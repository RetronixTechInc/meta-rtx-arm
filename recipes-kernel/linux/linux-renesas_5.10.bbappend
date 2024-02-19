DESCRIPTION = "Linux kernel for the R-Car V4x based boards"

COMPATIBLE_MACHINE = "(falcon|condor|eagle|whitehawk|raptor)"

#SRCREV = "${AUTOREV}"
SRCREV = "10eaa9ded9d3d88156c59dbe38e6fef857deae1f"

RTX_BSP_BRANCH = "main_sb"			
RTX_BSP_URL = "git://github.com/RetronixTechInc/rcar-kernel.git;protocol=https"
SRC_URI = "${RTX_BSP_URL};nocheckout=1;branch=${RTX_BSP_BRANCH}"

