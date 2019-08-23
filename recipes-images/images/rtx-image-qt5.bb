DESCRIPTION = "Freescale Image - Adds Qt5"
LICENSE = "MIT"

require recipes-fsl/images/fsl-image-qt5-validation-imx.bb

IMAGE_INSTALL_append += " \
wvdial \
wvdialconf \
rtxsetting \
"

IMAGE_FSTYPES= "tar.xz"
