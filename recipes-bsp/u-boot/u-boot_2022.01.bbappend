DESCRIPTION = "Linux u-boot for the R-Car V4x based board"


SRCREV = "${AUTOREV}"

# release v0.0.0 2025/03/07
#SRCREV = "728df72ea9ab208f8f594832d249d4df2af6133b"

# release v0.0.1 2025/03/28
#SRCREV = "d7b614847aba0d9c818353801095f13843638b59"


OVERRIDES .= ":${MACHINE}"
RTX_UBOOT_URL = "git://github.com/RetronixTechInc/rcar-uboot.git;protocol=https"
RTX_BRANCH_raptor = "v4h-raptor/v2022.01/rcar-6.0.0.rc9"
RTX_BRANCH_v4h-sbc = "v4h-sbc/v2022.01/rcar-6.0.0.rc9"
RTX_BRANCH_hel = "v4h-hel/v2022.01/rcar-6.0.0.rc9"

SRC_URI = "${RTX_UBOOT_URL};branch=${RTX_BRANCH}"

