#include<stdio.h>
#include<stdlib.h>

int main(int argc, char *argv[]){
	FILE* fp=NULL;
	char c=55;
	int i,j;
	long fishArmy[9],n=0;
	fp=fopen("Day6_input.txt","r");
	if(fp==NULL)
		exit(0);
	for (i=0;i<9;i++){
		fishArmy[i]=0;
	}
	while(c!=EOF){
		c=fgetc(fp);
		if((c>='0')&&(c<='8')){
			n=c-48;
			(fishArmy[n])++;
		}
	}
	fclose(fp);
	for (j=0;j<256;j++){
		n=fishArmy[0];
		for (i=0;i<8;i++){
			fishArmy[i]=fishArmy[i+1];
		}
		(fishArmy[6])+=n;
		fishArmy[8]=n;
	}
	n=0;
	for (i=0;i<9;i++){
		printf("%dth day %ld fishs\n",i,fishArmy[i]);
		n+=fishArmy[i];
	}
	printf("result = %ld\n",n);
	return 0;
}