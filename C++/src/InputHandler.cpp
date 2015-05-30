#include "include/InputHandler.h"

SquareGraph InputHandler::readMap(string filename) {

	int mapDimension;
	string line;
	char type;
	ifstream inputFile(filename.c_str());
	if (inputFile) {
		try{
			inputFile >> mapDimension;
			getline(inputFile, line);
			SquareGraph graph(mapDimension);
			for(int i = 0; i<mapDimension;i++){
					getline(inputFile, line);
					for(int j=0; j<mapDimension; j++){
						if(line.at(j) == 'T'){
							graph.setFirstRobotPos(make_pair(i,j));
							type = ' ';
						}
						else if(line.at(j) == 'G'){
							graph.setSecondRobotPos(make_pair(i,j));
							type = ' ';
						}
						else
							type = line.at(j);
						graph.setCellValue(make_pair(i, j), type);
					}
					line.clear();
			}
			graph.calculateAllCosts();
			inputFile.close();
			return graph;
		}
		catch(exception &e){
			inputFile.close();
			throw runtime_error("Input file is not well formatted.");
		}
	}
	else{
		throw runtime_error("Could not open file");
	}
}
