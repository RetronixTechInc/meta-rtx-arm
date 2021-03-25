DESCRIPTION = "Freescale Image - Adds Qt5"
LICENSE = "MIT"

require recipes-fsl/images/fsl-image-qt5-validation-imx.bb

IMAGE_INSTALL_append += " \
wvdial \
wvdialconf \
rtxsetting \
ttf-droid-sans \
ttf-droid-sans-fallback \
ttf-droid-sans-mono \
ttf-droid-serif \
freetype \
xdotool	\
unclutter \
fsl-rc-local \
"

IMAGE_LINGUAS = "en-us zh-cn"

#IMAGE_FSTYPES= "tar.xz"

LICENSE_FLAGS_WHITELIST="commercial"
CORE_IMAGE_EXTRA_INSTALL += "chromium-x11"
