#include "libopsys.h"
#include "util.h"

typedef struct{
    char* name;
    int workingDays[7];
}Worker;



int main(){
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
            printf("%s ", workers[i].name);
            for (int j = 0; j < 7; j++) {
                printf("%d ", workers[i].workingDays[j]);
            }
            printf("\n");
    }


    int menu = 1;
    printf("What do you want to do? : \n");
    printf("1 - Add new worker \n");
    printf("2 - Edit the worker requirements \n");
    printf("3 - Edit the worker attribute \n");
    printf("4 - Fire worker \n");
    printf("5 - Quit \n");
    scanf("%d", &menu);
    while (menu >  5 || menu < 1)
    {
        printf("incorrect menu selection \n");
        scanf("%d", &menu);
    }
    if(menu == 1){
            char tmpName[100];
            char hireDays[7][100];
            int daysBin[7] = {0,0,0,0,0,0,0};
            int readDays = 0;
            printf("Enter name: ");
            scanf("%s", tmpName);
            while ((getchar()) != '\n');

            printf("Enter avaliable days (in hungarian with 1 space between all lowercase): ");
            char input[100];
            fgets(input, sizeof(input), stdin);
            input[strcspn(input, "\n")] = '\0';
            
            char* token = strtok(input, " ");
            while(token != NULL && readDays < 7) {
                strcpy(hireDays[readDays], token);
                readDays++;
                token = strtok(NULL, " ");
            }

            printf("They were hired for the following day(s):");
            for (size_t i = 0; i < readDays; i++){
                int dayIndex = dayToNum(hireDays[i]);
                if(dayIndex != 10 && daysReq[dayIndex] > workersGot[dayIndex]){
                    workersGot[dayIndex]++;
                    daysBin[dayIndex] = 1;
                    printf(" %s",hireDays[i]);
                }
                else if(dayIndex == 10){
                    printf("\n Incorrect input");
                    return 1;
                }
            }
            printf("\n");

            Worker* tmpWorker = (Worker*)malloc(sizeof(Worker));
            tmpWorker->name = tmpName;
            for (size_t i = 0; i < 7; i++){
                tmpWorker->workingDays[i] = daysBin[i];
            }

            numWorkers++;
            workers = (Worker*)realloc(workers, numWorkers * sizeof(Worker));
            workers[numWorkers - 1] = *tmpWorker;
            free(tmpWorker);
    }
    else if(menu == 2){
        printf("Current requirements for a each day :");
        for (size_t i = 0; i < 7; i++){
            printf(" %d",daysReq[i]);
        }
        printf("\n");
        printf("Give new requirments in (orderd Mon...Sun with 1 space between with integers): \n ");
        for (size_t i = 0; i < 7; i++){
            int tmp = 0;
            scanf("%d", &tmp);
            if(tmp < workersGot[i]){
                printf("You have more workers than you want to change it to, fire the workers first then edit. \n");
                return 1;
            }
            daysReq[i] = tmp;
        }

    }
    else if(menu == 3){
        int changeRow = 0;
        int changeWhat = 0;
        printf("Which workers attribut  do you want to change (give row number) ? \n");
        scanf("%d", &changeRow);
        changeRow--;
        if(changeRow < 0 || changeRow > numWorkers - 1){
            printf("Incorrect row number");
            return 1;
        }
        printf("What do you want to change 1- name, 2 - schedule: \n");
        scanf("%d", &changeWhat);
        if(changeWhat == 1){
            char newName[100];
            printf("Enter new name: \n");
            scanf("%s", newName);
            workers[changeRow].name = newName;
        }
        else if(changeWhat == 2){
            char hireDays[7][100];
            int readDays = 0;
            while ((getchar()) != '\n');
            printf("Give what days to change (in hungarian with 1 space between all lowercase): ");
            char input[100];
            fgets(input, sizeof(input), stdin);
            input[strcspn(input, "\n")] = '\0';
            
            char* token = strtok(input, " ");
            while(token != NULL && readDays < 7) {
                strcpy(hireDays[readDays], token);
                readDays++;
                token = strtok(NULL, " ");
            }
            for (size_t i = 0; i < readDays; i++){
                int dayIndex = dayToNum(hireDays[i]);
                if(dayIndex != 10 && daysReq[dayIndex] > workersGot[dayIndex] && workers[changeRow].workingDays[dayIndex] == 0){
                    workersGot[dayIndex]++;
                    workers[changeRow].workingDays[dayIndex] = 1;
                }
                else if(dayIndex != 10 && workers[changeRow].workingDays[dayIndex] == 1){
                    workersGot[dayIndex]--;
                    workers[changeRow].workingDays[dayIndex] = 0;
                }
                else if(dayIndex == 10){
                    printf("\n Incorrect input");
                    return 1;
                }
            }
        }
        else{
            printf("Incorrect selection");
            return 1;
        }


    }
    else if(menu == 4){
        int fireRow = 0;
        printf("Which worker's attribute do you want to change (give row number)?\n");
        scanf("%d", &fireRow);
        fireRow--;
        if (fireRow < 0 || fireRow >= numWorkers) {
            printf("Incorrect row number");
            return 1;
        }

        Worker* tmp = (Worker*) malloc(sizeof(Worker) * (numWorkers-1));
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
            }
            else if(i != fireRow && reachedRemove){
                tmp[i - 1].name = workers[i].name;
                for (size_t j = 0; j < 7; j++) {
                    tmp[i - 1].workingDays[j] = workers[i].workingDays[j];
                }
            }
            else{
                free(workers[i].name);
                reachedRemove = true;
            }
        }
        numWorkers--;
        workers = (Worker*) realloc(workers, numWorkers * sizeof(Worker));
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
    //Calc workerCount for each day after modifications
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
            printf("%s ", workers[i].name);
            for (int j = 0; j < 7; j++) {
                printf("%d ", workers[i].workingDays[j]);
            }
            printf("\n");
    }

    //Writing into data.txt and workers.txt 
    fptrData = fopen("data.txt","w");
    if(fptrData == NULL){
        printf("Failed to open file");
        return 1;
    }

    for (size_t i = 0; i < 7; i++){
        fprintf(fptrData,"%d ",daysReq[i]);
    }
    fprintf(fptrData,"\n");
    for (size_t i = 0; i < 7; i++){
        fprintf(fptrData,"%d ",workersGot[i]);
    }
    fclose(fptrData);
    fptrWorkers = fopen("workers.txt", "w");
    for (size_t i = 0; i < numWorkers; i++)
    {
        fprintf(fptrWorkers,"%s ",workers[i].name);
        for (size_t j = 0; j < 7; j++){
            fprintf(fptrWorkers,"%d ",workers[i].workingDays[j]);
        }
        fprintf(fptrData,"\n");
    }
    fclose(fptrWorkers);


    free(workers);
    return 0;
}