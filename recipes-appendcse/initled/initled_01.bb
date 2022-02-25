SUMMARY = "Initial led script"
DESCRIPTION = "Script to do any first led init, started as a systemd service which removes itself once finished"
LICENSE = "CLOSED"
PR = "r3"

SRC_URI =  " \
	file://initled.sh \
	file://initled.service \
	file://initLed \
"
inherit allarch systemd

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "initled.service"

do_compile () {
}

do_install () {
	install -d ${D}/etc/default
	install -m 0755 ${WORKDIR}/initLed ${D}/etc/default

	install -d ${D}/${sbindir}
	install -m 0755 ${WORKDIR}/initled.sh ${D}/${sbindir}

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/initled.service ${D}${systemd_unitdir}/system
}
