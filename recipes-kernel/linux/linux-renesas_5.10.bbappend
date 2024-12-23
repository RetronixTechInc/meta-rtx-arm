DESCRIPTION = "Linux kernel for the R-Car V4x based boards"

COMPATIBLE_MACHINE = "(falcon|condor|eagle|whitehawk|raptor|v4h-sbc|hel)"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "${AUTOREV}"
#SRCREV = "2810414dc6dcbea2a6cb72c41146d19c0576bdee"

OVERRIDES .= ":${MACHINE}"
RTX_BSP_BRANCH_raptor = "v4h-raptor/v5.10.147/rcar-5.2.0.rc19"
RTX_BSP_BRANCH_v4h-sbc = "v4h-sbc/v5.10.147/rcar-5.2.0.rc19"
RTX_BSP_BRANCH_hel = "v4h-hel/v5.10.147/rcar-5.2.0.rc19"
RTX_BSP_URL = "git://git@github.com/RetronixTechInc/rcar-gen4-kernel.git;protocol=ssh"
SRC_URI = "${RTX_BSP_URL};nocheckout=1;branch=${RTX_BSP_BRANCH} \
	file://config_builtin_lt9611.cfg \
	"

KERNEL_DTC_FLAGS += "-@"


