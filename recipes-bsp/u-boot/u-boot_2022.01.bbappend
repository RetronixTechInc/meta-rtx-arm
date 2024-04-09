DESCRIPTION = "Linux u-boot for the R-Car V4x based board"

#SRCREV = "${AUTOREV}"
SRCREV = "9a8d0e10cd5045351d8d0f5bcc7c265037035ed9"

RTX_UBOOT_URL = "git://github.com/RetronixTechInc/rcar-uboot.git;protocol=https"
RTX_BRANCH = "v4h-raptor/v2022.01/rcar-6.0.0.rc5"

SRC_URI = "${RTX_UBOOT_URL};branch=${RTX_BRANCH}"

