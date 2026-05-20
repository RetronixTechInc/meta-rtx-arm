DESCRIPTION = "Linux u-boot for the R-Car V4H based board"

COMPATIBLE_MACHINE = "raptor|sparrowhawk"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "${RTX_UBOOT_REV}"
SRC_URI = "${RTX_UBOOT_URL};branch=${RTX_UBOOT_BRANCH} \
	   ${RTX_UBOOT_EXTRA_CONFIGS} \
"
