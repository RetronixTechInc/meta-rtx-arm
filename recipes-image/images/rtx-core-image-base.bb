DESCRIPTION = "rtx demo image."
LICENSE = "MIT"

export IMAGE_NAME="${MACHINE}-${DATETIME}"
export IMAGE_LINK_NAME="${MACHINE}"

require recipes-core/images/core-image-base.bb

IMAGE_INSTALL += "	\
bash			\
net-tools		\
minicom			\
python			\
apache2			\
php			\
php-cli			\
perl			\
samba			\
ppp			\
openssl			\
openssh			\
i2c-tools		\
util-linux		\
tzdata			\
alsa-utils		\
udev-extraconf		\
imx-test		\
libgpiod		\
owfs			\
e2fsprogs		\
wvdial			\
appendcse		\
initscript		\
networkmanager		\
dhcp-server 		\
dhcp-client		\
init-ifupdown		\
rp-pppoe		\
ntpdate			\
miniupnpd		\
quagga			\
cronie			\
at			\
iptables		\
msmtp			\
squid			\
procps			\
iputils			\
iproute2		\
tcpdump			\
modemmanager		\
libmbim			\
"
