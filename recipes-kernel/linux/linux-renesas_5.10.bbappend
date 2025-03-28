DESCRIPTION = "Linux kernel for the R-Car V4x based boards"

COMPATIBLE_MACHINE = "(falcon|condor|eagle|whitehawk|raptor|v4h-sbc|hel)"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"


SRCREV = "${AUTOREV}"

# release v0.0.0 2025/03/07
#SRCREV = "154fe69ecc968e8ace9d509509ae5ed75e402df8"

# release v0.0.1 2025/03/28
#SRCREV = "a9dac8116a66f6d00ad8940074293096a3258bfa"


OVERRIDES .= ":${MACHINE}"
RTX_BSP_BRANCH_raptor = "v4h-raptor/v5.10.147/rcar-5.2.0.rc19"
RTX_BSP_BRANCH_v4h-sbc = "v4h-sbc/v5.10.147/rcar-5.2.0.rc19"
RTX_BSP_BRANCH_hel = "v4h-hel/v5.10.147/rcar-5.2.0.rc19"
RTX_BSP_URL = "git://git@github.com/RetronixTechInc/rcar-gen4-kernel.git;protocol=ssh"
SRC_URI = "${RTX_BSP_URL};nocheckout=1;branch=${RTX_BSP_BRANCH} \
	file://config_max96752.cfg \
	file://config_builtin_lt9611.cfg \
	file://config_USB_XHCI_PCI_RENESAS.cfg \
	file://config_ad2428.cfg \
	"

KERNEL_DTC_FLAGS += "-@"


