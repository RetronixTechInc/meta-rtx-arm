DESCRIPTION = "Linux kernel for the R-Car V4x based boards"

COMPATIBLE_MACHINE = "(whitehawk|v4h-sbc)"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"


SRCREV = "${AUTOREV}"

# release v0.0.0 2025/07/11
#SRCREV = "701acc0a9ee6e3554439896cc92285ef996fd25d"


OVERRIDES .= ":${MACHINE}"
RTX_BSP_BRANCH_v4h-sbc = "v4h-sbc/v6.12.29-2025-05-20"
RTX_BSP_URL = "git://git@github.com/RetronixTechInc/rcar-gen4-kernel.git;protocol=ssh"
SRC_URI = "${RTX_BSP_URL};nocheckout=1;branch=${RTX_BSP_BRANCH} \
		file://rcar_gen4_pcie.bin \
		file://renesas_usb_fw.mem \
		"

KERNEL_DTC_FLAGS += "-@"

do_configure:append() {
    # redefine config_extra_firmware_dir
    sed "s|^CONFIG_EXTRA_FIRMWARE_DIR=.*|CONFIG_EXTRA_FIRMWARE_DIR=\"${WORKDIR}\"|g" -i ${B}/.config
}

