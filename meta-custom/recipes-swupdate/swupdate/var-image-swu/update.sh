#!/bin/sh

# Simple logging function
log() {
    echo "UPDATE: $1" >&2
}

case "$1" in
    preinst)
        log "Pre-installation started"
        
        # Check if target partition exists
        if [ ! -b "/dev/mmcblk1p3" ]; then
            log "ERROR: Target device /dev/mmcblk1p3 does not exist"
            exit 1
        fi
        
        # Unmount target partition if mounted
        umount /dev/mmcblk1p3 2>/dev/null || true
        
        # Format target partition
        log "Formatting target partition"
        mkfs.ext4 /dev/mmcblk1p3 -F -L rootfs3 -q
        
        log "Pre-installation completed"
        ;;
        
    postinst)
        log "Post-installation started"
        
        # Update U-Boot environment to boot from updated partition
        # Comment out if fw_setenv is not working
        # fw_setenv mmcpart 3
        
        log "Post-installation completed"
        ;;
        
    postfailure)
        log "Post-failure cleanup started"
        
        # Restore original boot partition
        # fw_setenv mmcpart 2
        
        log "Post-failure cleanup completed"
        ;;
        
    *)
        log "Usage: $0 {preinst|postinst|postfailure}"
        exit 1
        ;;
esac

exit 0
