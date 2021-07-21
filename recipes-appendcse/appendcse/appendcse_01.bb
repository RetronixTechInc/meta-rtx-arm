# Copyright (C) 2020-2021 RTX-3110

SUMMARY = "Rtx modify"
DESCRIPTION = "Add for customer requirements"
HOMEPAGE = ""
LICENSE = "CLOSED"

SRC_URI = " \
	file://check_update \
	file://uramdisk-recovery.img \
	file://rtx_setenv \
	file://efm32cmd \
	file://resolv.conf \
	file://.test.conf \
	file://.sleep.gyro \
	file://nm-system-settings.conf \
	file://mbim-network.conf \
	file://usb-mount.sh \
	file://usb-mount@.service \
	file://mbim-network.conf \
           "

INSANE_SKIP_${PN} = "ldflags"

FILES_${PN} += " \
/etc/resolv.conf \
/etc/NetworkManager \
/home/root/.test.conf \
/home/root/.sleep.gyro \
/home/root/mbim-network.conf \
"

do_install() {
	install -d ${D}/${sbindir}
	install -m 0755 ${WORKDIR}/rtx_setenv ${D}/${sbindir}
	install -m 0755 ${WORKDIR}/efm32cmd ${D}/${sbindir}
	install -m 0755 ${WORKDIR}/check_update ${D}/${sbindir}

	install -d ${D}/etc/udev/scripts

	install -m 0644 ${WORKDIR}/resolv.conf ${D}/etc/
	install -d ${D}/etc/NetworkManager
	install -m 0644 ${WORKDIR}/nm-system-settings.conf ${D}/etc/NetworkManager/
	install -d ${D}/home/root
	install -m 0644 ${WORKDIR}/.test.conf ${D}/home/root
	install -m 0755 ${WORKDIR}/.sleep.gyro ${D}/home/root
	install -m 0644 ${WORKDIR}/uramdisk-recovery.img ${DEPLOY_DIR_IMAGE}/
	install -m 0644 ${WORKDIR}/mbim-network.conf ${D}/etc/

	install -d ${D}/etc/systemd/system
	ln -sf /dev/null ${D}/etc/systemd/system/systemd-backlight@backlight:buzzer.service

	install -m 0755 ${WORKDIR}/usb-mount.sh ${D}/${sbindir}
	install -m 0777 ${WORKDIR}/usb-mount@.service ${D}/etc/systemd/system/
	install -d ${D}/etc/udev/rules.d
	install -m 0644 ${WORKDIR}/99-local.rules ${D}/etc/udev/rules.d/
}

