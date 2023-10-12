package br.com.luanalves.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Tipos de modificadores
 * public
 * private
 * protected
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /*
     * String (texto)
     * Integer (int) numeros inteiros
     * Double (double) numeros com ponto flutuante 0.0000
     * Float (float) numeros com ponto flutuante 0.00000000
     * char (caracteres) 'a' 'b' 'c'
     * Date (data) 01/01/2020
     * Boolean (boolean) true ou false
     * Void (vazio) sem retorno apenas executa oq tem dentro
     */
    /*
     * informações viram dentro do Body da request
     */
    @PostMapping("/")
    public void create(@RequestBody UserModel userModel) {
        System.out.println(userModel.getName());
        System.out.println(userModel.getUsername());
        System.out.println(userModel.getPassword());

    }
}
