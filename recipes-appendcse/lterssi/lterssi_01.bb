SUMMARY = "Monitor LTE RSSI script"
DESCRIPTION = "Script to do any first boot init, started as a systemd service which removes itself once finished"
LICENSE = "CLOSED"
PR = "r3"

SRC_URI =  " \
	file://lteRSSI.sh \
	file://lteRSSI.service \
           "

FILES_${PN} += " \
${sbindir} \
${systemd_unitdir}/system \
"
inherit allarch systemd

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "lteRSSI.service"

do_compile () {
}

do_install () {
	install -d ${D}/${sbindir}
	install -m 0755 ${WORKDIR}/lteRSSI.sh ${D}/${sbindir}

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/lteRSSI.service ${D}${systemd_unitdir}/system
}
