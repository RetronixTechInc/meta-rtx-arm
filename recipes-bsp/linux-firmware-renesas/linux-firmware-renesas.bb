SUMMARY = "firmware for Renesas."
LICENSE = "CLOSED"
PR = "r0"

SRC_URI = "file://K2026090-renesas_usb_fw.mem \
          "

COMPATIBLE_MACHINE = "(falcon|condor|eagle|whitehawk|raptor|v4h-sbc)"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/"

#Error message : ... doesn't have GNU_HASH (didn't pass LDFLAGS?) [ldflags]
TARGET_CC_ARCH += "${LDFLAGS}"


do_install() {
	install -d ${D}/lib/firmware/
	install -m 0644 ${S}/K2026090-renesas_usb_fw.mem ${D}/lib/firmware/renesas_usb_fw.mem
}

#add bellow if installing is not by oe_runmake install.
#Error message : ...  Files/directories were installed but not shipped in any package:
FILES_${PN} += "/lib/firmware/renesas_usb_fw.mem"



