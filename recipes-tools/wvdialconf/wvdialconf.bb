DESCRIPTION = "wvdial conf file"
DEPENDS = "wvdial"
LICENSE = "MIT"
PV = "1"
PR = "r0"

SRC_URI = " \
          file://wvdial.conf \
          file://README \
          "

LIC_FILES_CHKSUM = "file://README;md5=a7e940a99d82f17dee0c881c64e07623"
S = "${WORKDIR}"

do_install () {
	install -d ${D}${sysconfdir}/
	install -m 0755 ${S}/wvdial.conf ${D}${sysconfdir}/
}

FILES_${PN} = "${sysconfdir}/wvdial.conf "
