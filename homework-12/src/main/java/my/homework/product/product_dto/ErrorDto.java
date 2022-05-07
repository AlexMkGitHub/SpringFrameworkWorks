package my.homework.product.product_dto;

import my.homework.product.product_controller.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

public  class ErrorDto extends RuntimeException {

private Model model;
private Exception ex;

    @Autowired
    public ErrorDto(Model model, Exception ex) {
        this.model = model;
        this.ex = ex;
    }

    public ErrorDto(Exception ex) {
        this.ex = ex;
    }

    @ExceptionHandler
    public static String notFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    public static String illegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    public static String sqlException(SQLException ex) {
        return ex.getMessage();
    }

}
