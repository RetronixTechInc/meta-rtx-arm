DESCRIPTION = "rtx setting command"
#DEPENDS = "zlib"
SECTION = "libs"
LICENSE = "MIT"
PV = "1"
PR = "r0"

SRC_URI = " \
          file://src/ \
          "

LIC_FILES_CHKSUM = "file://common.h;md5=18f7cd9f557d41fb3cc2abcbb9a5ccf1"
S = "${WORKDIR}/src"
do_compile () {
    make
}

do_install () {
	install -d ${D}${bindir}/
	install -m 0755 ${S}/rtx_setting ${D}${bindir}/
    install -m 0755 ${S}/rtx_setenv ${D}${bindir}/
}

FILES_${PN} = "${bindir}/rtx_setting \
               ${bindir}/rtx_setenv "
