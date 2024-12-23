DESCRIPTION = "Linux u-boot for the R-Car V4x based board"

SRCREV = "${AUTOREV}"
#SRCREV = "9a8d0e10cd5045351d8d0f5bcc7c265037035ed9"

OVERRIDES .= ":${MACHINE}"
RTX_UBOOT_URL = "git://github.com/RetronixTechInc/rcar-uboot.git;protocol=https"
RTX_BRANCH_raptor = "v4h-raptor/v2022.01/rcar-6.0.0.rc9"
RTX_BRANCH_v4h-sbc = "v4h-sbc/v2022.01/rcar-6.0.0.rc9"
RTX_BRANCH_hel = "v4h-hel/v2022.01/rcar-6.0.0.rc9"

SRC_URI = "${RTX_UBOOT_URL};branch=${RTX_BRANCH}"

