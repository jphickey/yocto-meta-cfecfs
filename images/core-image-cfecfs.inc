# List of additional packages to support CFE/CFS on embedded linux

# Add some extra space in the image so there is room for CFE/CFS
IMAGE_ROOTFS_EXTRA_SPACE_append = " + 65536"

# install the kernel modules
IMAGE_INSTALL += "kernel-modules"

# Time synchronization - this adds dependency to oe "networking" layer
IMAGE_INSTALL += "ntp"

# Graphics and image processing libraries
#IMAGE_INSTALL += "openjpeg libjpeg-turbo libpng"

# SSH and related tools for remote access
IMAGE_INSTALL += "packagegroup-core-ssh-dropbear"

# Useful tools for file transfer
IMAGE_INSTALL += "lrzsz rsync"
IMAGE_INSTALL += "tftp-hpa"

# Filesystem and MTD device tools
IMAGE_INSTALL += "mtd-utils e2fsprogs dosfstools"

# Generic file compression tools/libraries
IMAGE_INSTALL += "zlib libbz2 xz gzip bzip2 lz4"

# Support for CFE/STRS OE using embedded webserver/FCGI/JSON
IMAGE_INSTALL += "fcgi json-c lighttpd lua"

# Additional lighttpd modules
IMAGE_INSTALL += "lighttpd-module-fastcgi lighttpd-module-compress lighttpd-module-alias lighttpd-module-access lighttpd-module-accesslog"

# CFS should use a read-only root file system to enhance robustness in the possibility of
# spurious/unclean reboots
IMAGE_FEATURES += "read-only-rootfs"

# Configuration for the SDK generated with 'populate_sdk' 
# Include kernel source so modules can be compiled by the SDK
TOOLCHAIN_TARGET_TASK_append = " kernel-dev"

# Include static development libraries 
SDKIMAGE_FEATURES_append = " staticdev-pkgs"

