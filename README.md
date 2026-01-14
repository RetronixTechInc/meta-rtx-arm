#  meta-rtx

This README file contains information on the contents of
the meta-rtx layer which provide support for RTX ported
boards based on Renesas RCar-V4H SoC.

Please see the corresponding sections below for details.

## Dependencies
============
This layer depends on:

  URI: git://git.yoctoproject.org/poky
  layers: meta, meta-poky, meta-yocto-bsp
  branch: scarthgap

  URI: git://git.openembedded.org/meta-openembedded
  layers: meta-oe, meta-python
  branch: scarthgap

  URI: https://github.com/renesas-rcar/meta-renesas.git
  layers: meta-rcar-bsp
  branch: scarthgap-dev

  URI: https://github.com/renesas-rcar/meta-renesas.git
  layers: meta-rcar-adas
  branch: scarthgap

