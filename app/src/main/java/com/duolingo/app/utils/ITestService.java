package com.duolingo.app.utils;

import java.util.ArrayList;

public interface ITestService {

    // LipeRMI - Obtener la lista de CURSOS disponibles [APP]
    public ArrayList<String> getResponse(short originLang);

}
