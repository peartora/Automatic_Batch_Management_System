package com.example.batchmanagingsystem.error;


public class WrongPartTypeError extends AppError {
    public WrongPartTypeError() {
        super("WRONG_PART_TYPE", "관리대상이 아닌 부품입니다.");
    }
}
