#include "util.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "libopsys.h"
#include <mqueue.h>

#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>




#define MSGSIZE 40
#define MAXMSGS 5

typedef struct{
    char* name;
    int workingDays[7];
}Worker;

void handler(int signumber) {
    printf("[handle] Bus is ready \n");
}




int main(int argc, char *argv[]){
    FILE *fptrData;
    FILE *fptrWorkers;
    fptrData = fopen("data.txt", "r");
    int numWorkers = 0;
    Worker* workers = NULL;

    if(fptrData == NULL){
        printf("Failed to open file");
        return 1;
    }
    int daysReq[7] = {0,0,0,0,0,0,0};
    int workersGot[7] = {0,0,0,0,0,0,0};

    if (NULL != fptrData) {
            for (size_t i = 0; i < 7; i++){
                fscanf(fptrData, "%d", &daysReq[i]);
            }
            for (size_t i = 0; i < 7; i++){
                fscanf(fptrData, "%d", &workersGot[i]);;
            }
        
    }
    fclose(fptrData);
    
    fptrWorkers = fopen("workers.txt", "r");
    if(fptrWorkers == NULL){
        printf("Failed to open file");
        return 1;
    }
    if (NULL != fptrWorkers) {
        fseek (fptrWorkers, 0, SEEK_END);
        int size = ftell(fptrWorkers);
        fseek(fptrWorkers,0, SEEK_SET);
        if(size == 0){
            printf("%i \n", size);
            printf("Empty workers.txt");
        }
        else{
            char line[100];
            while(fgets(line, sizeof(line), fptrWorkers) != NULL) {
                Worker* tmpWorker = (Worker*)malloc(sizeof(Worker));
                if(!tmpWorker){
                    printf("Memory allocation error");
                    return 1;
                }
                char* token = strtok(line, " ");
                char* tmpName = (char*)malloc(strlen(token)+1); 
                strcpy(tmpName, token);
                tmpWorker->name = tmpName;
                //printf("%s \n", tmpName);
                for(int i = 0; i < 7; i++) {
                    token = strtok(NULL, " ");
                    if(strcmp(token,"0") == 0){
                        tmpWorker->workingDays[i] = 0;
                    }
                    else{
                        tmpWorker->workingDays[i] = 1;
                    }
                }
                numWorkers++;
                workers = (Worker*)realloc(workers, numWorkers * sizeof(Worker));
                if (!workers) {
                    printf("Memory allocation error");
                    return 1;
                }
                workers[numWorkers - 1] = *tmpWorker;
                free(tmpWorker);
            }

            fclose(fptrWorkers);
        } 
    }
    for (size_t i = 0; i < 7; i++){
        int sum = 0;
        for (size_t j = 0; j < numWorkers ; j++){
            sum+=workers[j].workingDays[i];
        }
        workersGot[i] = sum;
    }

    printf("Current state of the wineyard: \n");
    printf("Required workers for each day: \n");

    for (int i = 0; i < 7; i++) {
        printf("%d ", daysReq[i]);
    }
    printf("\n");
    printf("Current worker count for each day: \n");
    for (int i = 0; i < 7; i++) {
        printf("%d ", workersGot[i]);
    }
    printf("\n");
    printf("Current workers: \n");
    for (int i = 0; i < numWorkers; i++) {
            printf("%d. worker: %s ", i + 1,workers[i].name);
            for (int j = 0; j < 7; j++) {
                printf("%d ", workers[i].workingDays[j]);
            }
            printf("\n");
    }


    int menu = 1;
    while (menu != 7) {
        printf("What do you want to do? : \n");
        printf("1 - Add new worker \n");
        printf("2 - Edit the worker requirements \n");
        printf("3 - Edit the worker attribute \n");
        printf("4 - Fire worker \n");
        printf("5 - List the workers on a given day \n");
        printf("6 - Start bus on a given day \n");
        printf("7 - Quit \n");
        scanf("%d", &menu);
        while (menu > 7 || menu < 1) {
            printf("incorrect menu selection \n");
            scanf("%d", &menu);
        }
        if (menu == 1) {
            char tmpName[100];
            char hireDays[7][100];
            int daysBin[7] = {0, 0, 0, 0, 0, 0, 0};
            int readDays = 0;
            printf("Enter a unique name that is not a part of an existing name: ");
            scanf("%s", tmpName);
            while ((getchar()) != '\n');
            for (int i = 0; i < numWorkers ; ++i) {
                if(strstr(workers[i].name,tmpName) != NULL){
                    printf(" Error: Non unique name \n");
                    return 0;
                }
            }

            printf("Enter avaliable days (in hungarian with 1 space between all lowercase): ");
            char input[100];
            fgets(input, sizeof(input), stdin);
            input[strcspn(input, "\n")] = '\0';

            char *token = strtok(input, " ");
            while (token != NULL && readDays < 7) {
                strcpy(hireDays[readDays], token);
                readDays++;
                token = strtok(NULL, " ");
            }

            printf("They were hired for the following day(s):");
            for (size_t i = 0; i < readDays; i++) {
                int dayIndex = dayToNum(hireDays[i]);
                if (dayIndex != 10 && daysReq[dayIndex] > workersGot[dayIndex]) {
                    workersGot[dayIndex]++;
                    daysBin[dayIndex] = 1;
                    printf(" %s", hireDays[i]);
                } else if (dayIndex == 10) {
                    printf("\n Incorrect input");
                    return 1;
                }
            }
            printf("\n");

            Worker *tmpWorker = (Worker *) malloc(sizeof(Worker));
            tmpWorker->name = tmpName;
            for (size_t i = 0; i < 7; i++) {
                tmpWorker->workingDays[i] = daysBin[i];
            }

            numWorkers++;
            workers = (Worker *) realloc(workers, numWorkers * sizeof(Worker));
            workers[numWorkers - 1] = *tmpWorker;
            free(tmpWorker);
        }
        else if (menu == 2) {
            printf("Current requirements for a each day :");
            for (size_t i = 0; i < 7; i++) {
                printf(" %d", daysReq[i]);
            }
            printf("\n");
            printf("Give new requirments in (orderd Mon...Sun with 1 space between with integers): \n ");
            for (size_t i = 0; i < 7; i++) {
                int tmp = 0;
                scanf("%d", &tmp);
                if (tmp < workersGot[i]) {
                    printf("You have more workers than you want to change it to, fire the workers first then edit. \n");
                    return 1;
                }
                daysReq[i] = tmp;
            }

        }
        else if (menu == 3) {
            int changeRow = 0;
            int changeWhat = 0;
            printf("Which workers attribut  do you want to change (give row number) ? \n");
            scanf("%d", &changeRow);
            changeRow--;
            if (changeRow < 0 || changeRow > numWorkers - 1) {
                printf("Incorrect row number");
                return 1;
            }
            printf("What do you want to change 1- name, 2 - schedule: \n");
            scanf("%d", &changeWhat);
            if (changeWhat == 1) {
                char newName[100];
                printf("Enter new name: \n");
                scanf("%s", newName);
                workers[changeRow].name = newName;
            } else if (changeWhat == 2) {
                char hireDays[7][100];
                int readDays = 0;
                while ((getchar()) != '\n');
                printf("Give what days to change (in hungarian with 1 space between all lowercase): ");
                char input[100];
                fgets(input, sizeof(input), stdin);
                input[strcspn(input, "\n")] = '\0';

                char *token = strtok(input, " ");
                while (token != NULL && readDays < 7) {
                    strcpy(hireDays[readDays], token);
                    readDays++;
                    token = strtok(NULL, " ");
                }
                for (size_t i = 0; i < readDays; i++) {
                    int dayIndex = dayToNum(hireDays[i]);
                    if (dayIndex != 10 && daysReq[dayIndex] > workersGot[dayIndex] &&
                        workers[changeRow].workingDays[dayIndex] == 0) {
                        workersGot[dayIndex]++;
                        workers[changeRow].workingDays[dayIndex] = 1;
                    } else if (dayIndex != 10 && workers[changeRow].workingDays[dayIndex] == 1) {
                        workersGot[dayIndex]--;
                        workers[changeRow].workingDays[dayIndex] = 0;
                    } else if (dayIndex == 10) {
                        printf("\n Incorrect input");
                        return 1;
                    }
                }
            } else {
                printf("Incorrect selection");
                return 1;
            }


        }
        else if (menu == 4) {
            int fireRow = 0;
            printf("Which worker's attribute do you want to change (give row number)?\n");
            scanf("%d", &fireRow);
            fireRow--;
            if (fireRow < 0 || fireRow >= numWorkers) {
                printf("Incorrect row number");
                return 1;
            }

            Worker *tmp = (Worker *) malloc(sizeof(Worker) * (numWorkers - 1));
            if (!tmp) {
                printf("Memory allocation error");
                return 1;
            }

            bool reachedRemove = false;
            for (size_t i = 0; i < numWorkers; i++) {
                if (i != fireRow && !reachedRemove) {
                    tmp[i].name = workers[i].name;
                    for (size_t j = 0; j < 7; j++) {
                        tmp[i].workingDays[j] = workers[i].workingDays[j];
                    }
                } else if (i != fireRow && reachedRemove) {
                    tmp[i - 1].name = workers[i].name;
                    for (size_t j = 0; j < 7; j++) {
                        tmp[i - 1].workingDays[j] = workers[i].workingDays[j];
                    }
                } else {
                    free(workers[i].name);
                    reachedRemove = true;
                }
            }
            numWorkers--;
            workers = (Worker *) realloc(workers, numWorkers * sizeof(Worker));
            if (!workers) {
                printf("Memory allocation error");
                return 1;
            }

            for (size_t i = 0; i < numWorkers; i++) {
                workers[i].name = tmp[i].name;
                for (size_t j = 0; j < 7; j++) {
                    workers[i].workingDays[j] = tmp[i].workingDays[j];
                }
            }
            free(tmp);
        }
        else if (menu == 5) {
            char listedDay[100];
            printf("Give a day: \n");
            scanf("%s", listedDay);
            int dayCode = dayToNum(listedDay);
            if (dayCode != 10) {
                printf("Workers on %s:  \n", listedDay);
                for (size_t i = 0; i < numWorkers; i++) {
                    if (workers[i].workingDays[dayCode] == 1) {
                        printf("%s \n", workers[i].name);
                    }
                }

            }
        }

        else if(menu == 6){
            char listedDay[100];
            printf("Give a day: \n");
            scanf("%s", listedDay);
            int count = 0;
            int dayCode = dayToNum(listedDay);
            if (dayCode != 10) {
                for (size_t i = 0; i < numWorkers; i++) {
                    if (workers[i].workingDays[dayCode] == 1) {
                        count++;
                    }
                }
            }

            pid_t cpid, ppid;
            ppid = getpid();

            struct mq_attr attr;
            char *mqname = "/mq";
            char rcv_buf[MSGSIZE];
            mqd_t mqdes1, mqdes2;
            attr.mq_maxmsg = MAXMSGS;
            attr.mq_msgsize = MSGSIZE;
            mqdes1 = mq_open(mqname, O_CREAT | O_RDWR, 0600, &attr);

            int pipe1[2];
            int pipe2[2];
            if (pipe(pipe1) == -1)
            {
                perror("pipe Failed");
                return 0;
            }
            if (pipe(pipe2) == -1)
            {
                perror("pipe Failed");
                return 0;
            }


            int child1 = -2;
            int child2 = -2;
            if(count > 0){
                //printf("child 1 fork called   \n");
                child1 = fork();
                if(child1 < 0){
                    perror("fork failed");
                    exit(1);
                }

                if(count > 5 && child1 == 0){
                    //printf("child 2 fork called   \n");
                    child2 = fork();
                    if(child2 < 0){
                        perror("fork failed");
                        exit(1);
                    }
                }
            }
            else{
                printf("No wokrkers for that day so no bus started \n");
            }

            signal(SIGINT,handler);

            if(child1 > 0 && (child2 > 0 || child2 == -2)){ // parent
                /*
                if(signal(SIGINT,handler) == SIG_ERR)
                    printf("Signal processed ");
                while (wait(NULL) > 0){};
                */
                //signal(SIGINT,handler);
                pause();

                //printf("parent \n");
                close(pipe1[0]);
                close(pipe2[0]);
                int workerCounter = 0;

                printf("%i daycode  \t numWorker: %i \n", dayCode,numWorkers);

                for (size_t i = 0; i < numWorkers; i++) {
                    if (workers[i].workingDays[dayCode] == 1) {
                        if(workerCounter < 5){
                            write(pipe1[1], workers[i].name, strlen(workers[i].name));
                        }
                        else{
                            write(pipe2[1], workers[i].name, strlen(workers[i].name));
                        }
                        workerCounter++;

                    }
                }
                close(pipe1[1]);
                close(pipe2[1]);
                //sleep(1);
                while (wait(NULL) > 0){};
                if(workerCounter > 5){
                    mq_receive(mqdes1, rcv_buf, MSGSIZE, 0);
                    printf("Bus 2: %s worker arrived\n", rcv_buf);
                    mq_receive(mqdes1, rcv_buf, MSGSIZE, 0);
                    printf("Bus 1: %s worker arrived\n", rcv_buf);
                }
                else{
                    mq_receive(mqdes1, rcv_buf, MSGSIZE, 0);
                    printf("Bus 1: %s worker arrived\n", rcv_buf);
                }
                //printf(" \n PId (pid= %i)\n", getpid());
            }
            else if(child1 == 0 && child2 != 0){ //child
                cpid = getpid();
                if(count > 5) {
                    /*if (signal(SIGINT, handler) == SIG_ERR)
                        printf("Signal processed ");
                    while (wait(NULL) > 0) {};*/
                    pause();
                }
                kill(ppid, SIGINT);
                if(count > 5) {
                    while (wait(NULL) > 0) {};
                }

                printf(" \n ---------BUS 1 -------  \n");

                //printf("Child (pid= %i)\n", getpid());
                close(pipe1[1]);
                int bufsize = 1024;
                char buf[1024];
                int arriveCounter = 0;
                while (read(pipe1[0], buf, bufsize) > 0) {
                    //printf("Received string: %s\n", buf);
                    close(pipe1[0]);
                }
                for (int i = 0; i < numWorkers ; ++i) {
                    if(strstr(buf,workers[i].name)){
                            printf("%s \n",workers[i].name);
                            arriveCounter++;
                    }
                }
                char int_str[20];

                sprintf(int_str, "%d", arriveCounter);

                mqdes2 = mq_open(mqname, O_CREAT | O_RDWR, 0600, &attr);
                mq_send(mqdes2, int_str, MSGSIZE, 30);
                mq_close(mqdes2);

                return 0;
            }
            else if(child2 == 0){ //child2
                cpid = getppid();
                kill(cpid, SIGINT);

                printf(" \n--------- BUS 2 ---------  \n");
                //printf("Child2 (pid= %i)\n", getpid());
                close(pipe2[1]);
                int bufsize = 1024;
                char buf[1024];
                int arriveCounter = 0;
                while (read(pipe2[0], buf, bufsize) > 0) {
                    //printf("Received string: %s\n", buf);
                    close(pipe2[0]);
                }
                for (int i = 0; i < numWorkers ; ++i) {
                    if(strstr(buf,workers[i].name)){
                            printf("%s \n",workers[i].name);
                            arriveCounter++;
                    }
                }
                char int_str[20];
                sprintf(int_str, "%d", arriveCounter);

                mqdes2 = mq_open(mqname, O_CREAT | O_RDWR, 0600, &attr);
                mq_send(mqdes2, int_str, MSGSIZE, 30);
                mq_close(mqdes2);

                return 0;
            }
            mq_unlink(mqname);



        }


        for (size_t i = 0; i < 7; i++) {
            int sum = 0;
            for (size_t j = 0; j < numWorkers; j++) {
                sum += workers[j].workingDays[i];
            }
            workersGot[i] = sum;
        }


        printf("Current state of the wineyard: \n");
        printf("Required workers for each day: \n");

        for (int i = 0; i < 7; i++) {
            printf("%d ", daysReq[i]);
        }
        printf("\n");
        printf("Current worker count for each day: \n");
        for (int i = 0; i < 7; i++) {
            printf("%d ", workersGot[i]);
        }
        printf("\n");
        printf("Current workers: \n");
        for (int i = 0; i < numWorkers; i++) {
            printf("%d. worker:  %s ", i + 1, workers[i].name);
            for (int j = 0; j < 7; j++) {
                printf("%d ", workers[i].workingDays[j]);
            }
            printf("\n");
        }
        fptrData = fopen("data.txt", "w");
        if (fptrData == NULL) {
            printf("Failed to open file");
            return 1;
        }

        for (size_t i = 0; i < 7; i++) {
            fprintf(fptrData, "%d ", daysReq[i]);
        }
        fprintf(fptrData, "\n");
        for (size_t i = 0; i < 7; i++) {
            fprintf(fptrData, "%d ", workersGot[i]);
        }
        fclose(fptrData);
        fptrWorkers = fopen("workers.txt", "w");
        for (size_t i = 0; i < numWorkers; i++) {
            fprintf(fptrWorkers, "%s ", workers[i].name);
            for (size_t j = 0; j < 7; j++) {
                fprintf(fptrWorkers, "%d ", workers[i].workingDays[j]);
            }
            fprintf(fptrData, "\n");
        }
        fclose(fptrWorkers);
    }


    free(workers);
    return 0;
}