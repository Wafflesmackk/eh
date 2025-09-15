#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <string.h>

#ifndef UTIL_H
#define UTIL_H

int dayToNum(char day[]){

    if(strcmp(day,"hetfo") == 0){
        return 0;
    }
    else if(strcmp(day,"kedd") == 0){
        return 1;
    }
    else if(strcmp(day,"szerda") == 0){
        return 2;
    }
    else if(strcmp(day,"csutortok") == 0){
        return 3;
    }
    else if(strcmp(day,"pentek") == 0){
        return 4;
    }
    else if(strcmp(day,"szombat") == 0){
        return 5;
    }
    else if(strcmp(day,"vasarnap") == 0){
        return 6;
    }
    else{
        return 10;
    }
}



#endif 