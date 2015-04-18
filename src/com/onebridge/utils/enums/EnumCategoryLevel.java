package com.onebridge.utils.enums;

public enum EnumCategoryLevel {

	
    ROOT  ("ROOT"),  //calls constructor with value 3
    BRANCH("BRNACH"),  //calls constructor with value 2
    LEAF   ("LEAF")   //calls constructor with value 1
    ; // semicolon needed when fields / methods follow


    private final String levelCode;

    EnumCategoryLevel(String levelCode) {
        this.levelCode = levelCode;
    }
    
    public String getLevelCode() {
        return this.levelCode;
    }

    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return this.levelCode;
    }
    
}
