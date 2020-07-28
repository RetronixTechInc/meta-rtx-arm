# Copyright (C) 2020-2021 RTX-3110

SUMMARY = "Rtx modify"
DESCRIPTION = "Add for customer requirements"
HOMEPAGE = ""
LICENSE = "CLOSED"

SRC_URI = " \
	file://11-media-by-label-auto-mount.rules \
	file://uramdisk-recovery.img \
	file://rtx_setenv \
	file://efm32cmd \
	file://resolv.conf \
	file://.test.conf \
	file://.sleep.gyro \
           "
INSANE_SKIP_${PN} = "ldflags"

FILES_${PN} += " \
/etc/udev/rules.d/11-media-by-label-auto-mount.rules \
/etc/resolv.conf \
/home/root/.test.conf \
/home/root/.sleep.gyro \
"

do_install() {
	install -d ${D}/${sbindir}
	install -m 0755 ${WORKDIR}/rtx_setenv ${D}/${sbindir}
	install -m 0755 ${WORKDIR}/efm32cmd ${D}/${sbindir}

	install -d ${D}/etc/udev/rules.d
	install -m 0644 ${WORKDIR}/11-media-by-label-auto-mount.rules ${D}/etc/udev/rules.d
	install -m 0644 ${WORKDIR}/resolv.conf ${D}/etc/
	install -d ${D}/home/root
	install -m 0644 ${WORKDIR}/.test.conf ${D}/home/root
	install -m 0755 ${WORKDIR}/.sleep.gyro ${D}/home/root
	install -m 0644 ${WORKDIR}/uramdisk-recovery.img ${DEPLOY_DIR_IMAGE}/
}


