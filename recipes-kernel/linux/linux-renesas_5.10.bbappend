DESCRIPTION = "Linux kernel for the R-Car V4H based boards"

COMPATIBLE_MACHINE = "raptor|sparrowhawk"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "${RTX_KERNEL_REV}"
SRC_URI = "${RTX_KERNEL_URL};nocheckout=1;branch=${RTX_KERNEL_BRANCH} \
	   ${RTX_KERNEL_EXTRA_CONFIGS} \
"

# Remove this patch to prevent from do_patch() error
SRC_URI:remove = "file://init_disassemble_info-signature-changes-causes-compile-failures.patch"

KERNEL_DTC_FLAGS += "-@"
