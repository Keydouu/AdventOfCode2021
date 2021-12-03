#include<stdio.h>
#include<stdlib.h>
int main(int argc, char *argv[]){
	unsigned int gammaRate=0,epsilonRate=0,sizeOfBinaryNumber=0,i=0;
	unsigned int *onesCount=NULL,*zerosCount=NULL;
	char c=55;
	FILE* fp=NULL;
	fp=fopen("Day3_input.txt","r");
	if(fp==NULL)
		exit(0);
	c=fgetc(fp);
	while((c!=EOF)&&(c!='\n')){
		c=fgetc(fp);
		sizeOfBinaryNumber++;
	}
	rewind(fp);
	onesCount=(int*)malloc(sizeof(unsigned int)*sizeOfBinaryNumber);
	zerosCount=(int*)malloc(sizeof(unsigned int)*sizeOfBinaryNumber);
	if((onesCount==NULL)||(zerosCount==NULL))
		exit(0);
	while(c!=EOF){
		c=fgetc(fp);
		if(c=='1'){
			onesCount[i]++;
			i++;
		}
		else if(c=='0'){
			zerosCount[i]++;
			i++;
		}
		else if(c=='\n')
			i=0;
	}
	fclose(fp);
	for (i = 0; i < sizeOfBinaryNumber; i++){
		if(onesCount[i]>zerosCount[i]){
			gammaRate=gammaRate*2+1;
			epsilonRate*=2;
		}
		else{
			gammaRate*=2;
			epsilonRate=epsilonRate*2+1;
		}
	}
	printf("Gamma rate = %d\n", gammaRate);
	printf("Epsilon rate = %d\n", epsilonRate);
	printf("power consumption = %d\n",(gammaRate*epsilonRate));
	return 0;
}