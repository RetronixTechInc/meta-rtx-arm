DESCRIPTION = "rtx demo image."
LICENSE = "MIT"

export IMAGE_NAME="${MACHINE}-${DATETIME}"
export IMAGE_LINK_NAME="${MACHINE}"

require dynamic-layers/qt5-layer/recipes-fsl/images/fsl-image-qt5-validation-imx.bb

IMAGE_INSTALL += "	\
bash			\
net-tools		\
minicom			\
python			\
php			\
php-cli			\
perl			\
samba			\
ppp			\
openssl			\
apache2			\
i2c-tools		\
util-linux		\
tzdata			\
alsa-utils		\
udev-extraconf		\
connman			\
initscript		\
imx-test		\
libgpiod		\
python-pygobject	\
gtk+3			\
appendcse		\
"


#gobject-introspection	\
