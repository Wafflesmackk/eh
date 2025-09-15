//
// Created by rontap on 20/03/2023.
//
#include "libopsys.h"

int main(int argc, char **argv) {
    /**
     * Task: Implement wrapper for kill
     * kill SIGNAL PID
     */

    // 1. TODO: Add check that there are 2 arguments
    if (argc != 3) {
        // 1b* TODO: extra task, optional
        // If there are no arguments, ask for a SIGNAL and PID from stdin
        printf("To use kill, add exactly two parameters.\n");
        printf("Usage: kill SIGNAL PID");
        exit(1);
    }


    // 2. TODO: Add check that PID is a number
    // use function `strtol` for conversion
    char *signal_overflow;
    char *pid_overflow;
    errno = 0;
    long int pid = strtol(argv[1], &pid_overflow, 10);
    long int sig = strtol(argv[2], &signal_overflow, 10);

    if(errno != 0 || *pid_overflow != '\0' || *signal_overflow != '\0'){
        printf("There was an error");
        exit(1);
    }
    // 3. TODO: send actual signal
    kill(pid,sig);
    // 4. TODO: if signal returns with -1, that means there was an error
    
    // 4b TODO: extra task, optional
    // check the errno variable, and printf out a message. errno can have these values:
    // EINVAL The value of the sig argument is an invalid or unsupported signal number.
    // EPERM  The process does not have permission to send the signal to any receiving process.
    // ESRCH  No process or process group can be found corresponding to that specified by pid.


    return 0;
}