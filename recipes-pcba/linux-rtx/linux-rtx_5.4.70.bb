# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2020 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux Kernel provided and supported by NXP"
DESCRIPTION = "Linux Kernel provided and supported by NXP with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native"

LOCALVERSION = "-2.3.0"

SRCBRANCH = "RTX_5.4.70_2.3.0"
SRC_URI = "git://github.com/RetronixTechInc/linux-rtx.git;protocol=git;branch=${SRCBRANCH}"
SRCREV = "RTX_5.4.70_2.3.0"

FILES_${KERNEL_PACKAGE_NAME}-base += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo "

KERNEL_CONFIG_COMMAND = "oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} olddefconfig"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEFAULT_PREFERENCE = "1"

DO_CONFIG_V7_COPY = "no"
DO_CONFIG_V7_COPY_mx6 = "yes"
DO_CONFIG_V7_COPY_mx7 = "yes"
DO_CONFIG_V7_COPY_mx8 = "no"

addtask copy_defconfig after do_patch before do_preconfigure

do_copy_defconfig () {
    install -d ${B}
    if [ ${DO_CONFIG_V7_COPY} = "yes" ]; then
        # copy latest imx_v7_defconfig to use for mx6, mx6ul and mx7
        mkdir -p ${B}
        cp ${S}/rtx/configs/rtx-imx6q-pitx-b21_defconfig ${B}/.config
        cp ${S}/rtx/configs/rtx-imx6q-pitx-b21_defconfig ${B}/../defconfig
    else
        # copy latest imx_v8_defconfig to use for mx8
        mkdir -p ${B}
        cp ${S}/arch/arm64/configs/imx_v8_defconfig ${B}/.config
        cp ${S}/arch/arm64/configs/imx_v8_defconfig ${B}/../defconfig
    fi
}

DELTA_KERNEL_DEFCONFIG ?= ""
#DELTA_KERNEL_DEFCONFIG_prepend_mx8 = "sdk_imx.config "

do_merge_delta_config[dirs] = "${B}"
do_merge_delta_config() {
    for deltacfg in ${DELTA_KERNEL_DEFCONFIG}; do
        if [ -f ${S}/arch/${ARCH}/configs/${deltacfg} ]; then
            ${KERNEL_CONFIG_COMMAND}
            oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} ${deltacfg}
        elif [ -f "${WORKDIR}/${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${WORKDIR}/${deltacfg}
        elif [ -f "${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${deltacfg}
        fi
    done
    cp .config ${WORKDIR}/defconfig
}
addtask merge_delta_config before do_preconfigure after do_copy_defconfig

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	if [ "${BUILD_REPRODUCIBLE_BINARIES}" = "1" ]; then
		# kernel sources do not use do_unpack, so SOURCE_DATE_EPOCH may not
		# be set....
		if [ "${SOURCE_DATE_EPOCH}" = "" -o "${SOURCE_DATE_EPOCH}" = "0" ]; then
			olddir=`pwd`
			cd ${S}
			SOURCE_DATE_EPOCH=`git log  -1 --pretty=%ct`
			# git repo not guaranteed, so fall back to REPRODUCIBLE_TIMESTAMP_ROOTFS
			if [ $? -ne 0 ]; then
				SOURCE_DATE_EPOCH=${REPRODUCIBLE_TIMESTAMP_ROOTFS}
			fi
			cd $olddir
		fi

		ts=`LC_ALL=C date -d @$SOURCE_DATE_EPOCH`
		export KBUILD_BUILD_TIMESTAMP="$ts"
		export KCONFIG_NOTIMESTAMP=1
		bbnote "KBUILD_BUILD_TIMESTAMP: $ts"
	fi
	# The $use_alternate_initrd is only set from
	# do_bundle_initramfs() This variable is specifically for the
	# case where we are making a second pass at the kernel
	# compilation and we want to force the kernel build to use a
	# different initramfs image.  The way to do that in the kernel
	# is to specify:
	# make ...args... CONFIG_INITRAMFS_SOURCE=some_other_initramfs.cpio
	if [ "$use_alternate_initrd" = "" ] && [ "" != "" ] ; then
		# The old style way of copying an prebuilt image and building it
		# is turned on via INTIRAMFS_TASK != ""
		copy_initramfs
		use_alternate_initrd=CONFIG_INITRAMFS_SOURCE=${B}/usr/${INITRAMFS_IMAGE_NAME}.cpio
	fi
	cc_extra=$(get_cc_option)
	for typeformake in uImage ; do
		oe_runmake CROSS_COMPILE=/usr/bin/arm-linux-gnueabihf- ${typeformake} -j8 LD="arm-poky-linux-gnueabi-ld.bfd" LOADADDR=0x10008000 $use_alternate_initrd
	done
	# vmlinux.gz is not built by kernel
	if (echo "uImage" | grep -wq "vmlinux\.gz"); then
		mkdir -p "arch/arm/boot"
		gzip -9cn < /media/alston/Yocto_Disk/yocto_sources/build_5470_fb/tmp/work/imx6q_ohga-poky-linux-gnueabi/linux-rtx/5.4.70-r0/build/vmlinux > "arch/arm/boot/vmlinux.gz"
	fi
}

COMPATIBLE_MACHINE = "(mx6|mx7|mx8)"

