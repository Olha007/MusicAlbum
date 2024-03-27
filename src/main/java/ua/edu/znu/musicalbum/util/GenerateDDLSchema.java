package ua.edu.znu.musicalbum.util;

import ua.edu.znu.musicalbum.service.AlbumDaoImpl;

import javax.persistence.Persistence;

/**
 * Use for DDL-script for drop tables and create tables generation.
 * You must enable properties for schema generation in the persistence.xml.
 */
public class GenerateDDLSchema {
    public static void main(String[] args) {
        Persistence.generateSchema("musicAlbumPU", null);
        /*For root directory printing*/
        System.out.println(System.getProperty("user.dir"));

        /*For Lombok's @String.Exclude checking*/
        AlbumDaoImpl albumDao = new AlbumDaoImpl();
        System.out.println(albumDao.findById(1L));

        System.exit(0);
    }
}
