DESCRIPTION = "Linux kernel for the R-Car V4x based boards"

COMPATIBLE_MACHINE = "(falcon|condor|eagle|whitehawk|raptor)"

#SRCREV = "${AUTOREV}"
SRCREV = "c752c6765af868b23fb099b17444048d67a0b645"

RTX_BSP_BRANCH = "v4h-raptor/v5.10.147/rcar-5.2.0.rc10"
RTX_BSP_URL = "git://github.com/RetronixTechInc/rcar-kernel.git;protocol=https"
SRC_URI = "${RTX_BSP_URL};nocheckout=1;branch=${RTX_BSP_BRANCH}"

