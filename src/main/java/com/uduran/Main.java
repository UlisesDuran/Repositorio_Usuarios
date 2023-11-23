package com.uduran;

import com.uduran.models.Usuario;
import com.uduran.repositorio.Repositorio;
import com.uduran.repositorio.UsuarioRepositorioImpl;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int opcionIndice = 0;

        Repositorio<Usuario> repositorio = new UsuarioRepositorioImpl();
        Map<String, Integer> operaciones = new HashMap<>();
        operaciones.put("Actualizar", 1);
        operaciones.put("Eliminar", 2);
        operaciones.put("Agregar", 3);
        operaciones.put("Listar", 4);
        operaciones.put("Salir", 5);

        Object[] opArreglo = operaciones.keySet().toArray();

        while (opcionIndice!=5){
            Object opcion = JOptionPane.showInputDialog(null,
                    "Seleccione un Operación",
                    "Mantenedor de Usuarios",
                    JOptionPane.INFORMATION_MESSAGE, null, opArreglo, opArreglo[0]);

            if (opcion == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una operación");
            } else {
                opcionIndice = operaciones.get(opcion.toString());
                switch (opcionIndice){
                    case 1 -> actualizar(repositorio);
                    case 2 -> eliminar(repositorio);
                    case 3 -> agregar(repositorio);
                    case 4 -> {
                        System.out.println("==============LISTA DE USUARIOS=============");
                        repositorio.listar().forEach(System.out::println);
                    }
                    case 5 -> System.out.println("Hasta pronto!");
                }
            }
        }
    }

    private static void agregar(Repositorio<Usuario> repo){
        Usuario u = new Usuario();
        Object option = JOptionPane.showInputDialog(null, "Escriba el nombre del usuario");
        u.setUsername(option.toString());
        option = JOptionPane.showInputDialog(null, "Escriba una contraseña");
        u.setPassword(option.toString());
        option = JOptionPane.showInputDialog(null, "Escribe un mail");
        u.setMail(option.toString());
        repo.guardar(u);
    }

    private static void eliminar(Repositorio<Usuario> repo){
        Object option = JOptionPane.showInputDialog(null, "Escriba el id del usuario a eliminar");
        repo.eliminar(Long.parseLong(option.toString()));
    }

    private static void actualizar(Repositorio<Usuario> repo){
        try{
            Usuario u = new Usuario();
            Object option = JOptionPane.showInputDialog(null, "Escriba el id del usuario a actualizar");
            u.setId(repo.porId(Long.parseLong(option.toString())).getId());
            option = JOptionPane.showInputDialog(null, "Escriba el nuevo nombre de usuario:");
            u.setUsername(option.toString());
            option = JOptionPane.showInputDialog(null, "Escriba la nueva contraseña:");
            u.setPassword(option.toString());
            u.setMail(repo.porId(Long.parseLong(option.toString())).getMail());
            repo.guardar(u);
        }catch (NumberFormatException e){
            System.out.println("El usuario no existe!");
        }
    }
}