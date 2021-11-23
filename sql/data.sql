
INSERT INTO CONTACTO_ENTITY (ID, CORREO, TELEFONO) VALUES (100, 'roberto@gmail.com', '+57 0987654321');
INSERT INTO CONTACTO_ENTITY (ID, CORREO, TELEFONO) VALUES (101, 'valepaternostro@gmail.com', '+57 7519203219');
INSERT INTO CONTACTO_ENTITY (ID, CORREO, TELEFONO) VALUES (102, 'medidogs@gmail.com', '+57 7471288838');

INSERT INTO VETERINARIO_ENTITY (ID ,CALIFICACION ,CERTIFICADO_ENTRENAMIENTO ,ESPECIALIDAD ,EXPERIENCIA_PREVIA ,NOMBRE ,CONTACTO_ID) VALUES (100, 3.4, 'saadasjdas', 'asdasdas', 'qeqwe', 'Roberto' ,100);
INSERT INTO VETERINARIO_ENTITY (ID ,CALIFICACION ,CERTIFICADO_ENTRENAMIENTO ,ESPECIALIDAD ,EXPERIENCIA_PREVIA ,NOMBRE ,CONTACTO_ID) VALUES (101, 3.9, 'aaasds', 'xmxmxm', 'qaaaeqwe', 'Antonia' ,null);
INSERT INTO VETERINARIO_ENTITY (ID ,CALIFICACION ,CERTIFICADO_ENTRENAMIENTO ,ESPECIALIDAD ,EXPERIENCIA_PREVIA ,NOMBRE ,CONTACTO_ID) VALUES (102, 4.1, 'asdnnc', 'jhndif', 'qeqweccc', 'Calamardo' ,null);

INSERT INTO CLIENTE_ENTITY (ID, NOMBRE, CALIFICACION ,CONTACTO_ID) VALUES (100, 'Valentina Paternostro', 3.8 ,101);
INSERT INTO CLIENTE_ENTITY (ID, NOMBRE, CALIFICACION ,CONTACTO_ID) VALUES (101, 'Laura Martínez', 4.0 ,null);
INSERT INTO CLIENTE_ENTITY (ID, NOMBRE, CALIFICACION ,CONTACTO_ID) VALUES (102, 'Lina Orozco', 3.5 ,null);

INSERT INTO EMPRESA_CONVENIO_ENTITY (ID, NOMBRE, NIT, SERVICIO ,CONTACTO_ID) VALUES (100, 'MediDogs', 7788, 'Operaciones' ,102);
INSERT INTO EMPRESA_CONVENIO_ENTITY (ID, NOMBRE, NIT, SERVICIO ,CONTACTO_ID) VALUES (101, 'Animals', 77389, 'Vacunación', null);
INSERT INTO EMPRESA_CONVENIO_ENTITY (ID, NOMBRE, NIT, SERVICIO ,CONTACTO_ID) VALUES (102, 'Dogs&Cats', 75790, 'Analisis' ,null);

INSERT INTO MASCOTA_ENTITY (ID ,FECHA_NACIMIENTO ,NOMBRE ,TIPO_MASCOTA ,RAZA ,UBICACION , EDAD, DUENIO_ID ) VALUES ( 100, '2020-10-10', 'Michi', 1 , 'Naranja', 'Bogota', 10, 100);
INSERT INTO MASCOTA_ENTITY (ID ,FECHA_NACIMIENTO ,NOMBRE ,TIPO_MASCOTA ,RAZA ,UBICACION , EDAD, DUENIO_ID ) VALUES ( 101, '2019-10-10', 'Firulais', 0 ,'Perro', 'Cali', 5, 101);
INSERT INTO MASCOTA_ENTITY (ID ,FECHA_NACIMIENTO ,NOMBRE ,TIPO_MASCOTA ,RAZA ,UBICACION , EDAD, DUENIO_ID ) VALUES ( 102, '2018-10-10', 'Mumble', 2 ,'Pinguino', 'Bogota', 1, 101);

INSERT INTO SERVICIO_ENTITY (ID ,NOMBRE ) VALUES ( 100, 'Vacunacion');
INSERT INTO SERVICIO_ENTITY (ID ,NOMBRE ) VALUES ( 101, 'Cirugia');
INSERT INTO SERVICIO_ENTITY (ID ,NOMBRE ) VALUES ( 102, 'Servicio bueno');

INSERT INTO AGENDA_ENTITY (ID, NUMERO_CITAS_PENDIENTES, NUMERO_CITAS_REALIZADAS, NUMERO_CITAS_CANCELADAS, VETERINARIO_ID) VALUES (100,0,1,2,100);
INSERT INTO AGENDA_ENTITY (ID, NUMERO_CITAS_PENDIENTES, NUMERO_CITAS_REALIZADAS, NUMERO_CITAS_CANCELADAS, VETERINARIO_ID) VALUES (101,1,1,1,101);
INSERT INTO AGENDA_ENTITY (ID, NUMERO_CITAS_PENDIENTES, NUMERO_CITAS_REALIZADAS, NUMERO_CITAS_CANCELADAS, VETERINARIO_ID) VALUES (102,2,1,0,102);

INSERT INTO CITA_ENTITY (ID, FECHA, ESTADO, COSTO, DISPONIBLE, DURACION, PACIENTE_ID, AGENDA_ID) VALUES (100,'2021-11-15T06:00:00',0,5000.0,false,120, 100, 100);
INSERT INTO CITA_ENTITY (ID, FECHA, ESTADO, COSTO, DISPONIBLE, DURACION, PACIENTE_ID, AGENDA_ID) VALUES (101,'2021-11-17T07:00:00',1,5000.0,true,60, 101, 101);
INSERT INTO CITA_ENTITY (ID, FECHA, ESTADO, COSTO, DISPONIBLE, DURACION, PACIENTE_ID, AGENDA_ID) VALUES (102,'2021-11-19T12:00:00',2,5000.0,true,180, 100, 102);

INSERT INTO REGISTRO_MEDICO_ENTITY(ID, FECHA_EXPEDICION, IDENTIFICACION, IMAGEN,VETERINARIO_ID) VALUES(131,'2021-05-09','1000203942','foto',100);
INSERT INTO REGISTRO_MEDICO_ENTITY(ID, FECHA_EXPEDICION, IDENTIFICACION, IMAGEN,VETERINARIO_ID) VALUES(132,'2001-09-12','1000201322','foto 1',101);
INSERT INTO REGISTRO_MEDICO_ENTITY(ID, FECHA_EXPEDICION, IDENTIFICACION, IMAGEN,VETERINARIO_ID) VALUES(133,'2030-10-08','1000476574','foto 2',102);

INSERT INTO CALIFICACION_ENTITY(ID, CREADOR,FECHA, PUNTAJE, CLIENTE_CALIFICADO_ID,VETERINARIO_CALIFICADO_ID,DESCRIPCION) VALUES(141,'Pepe','2021-10-09',3 ,null,101,'Un veterinario normal');
INSERT INTO CALIFICACION_ENTITY(ID, CREADOR,FECHA, PUNTAJE, CLIENTE_CALIFICADO_ID,VETERINARIO_CALIFICADO_ID,DESCRIPCION) VALUES(142,'Jenkins','2021-02-01',3 ,102,null,'Un cliente un poco sobreprotector');
INSERT INTO CALIFICACION_ENTITY(ID, CREADOR,FECHA, PUNTAJE, CLIENTE_CALIFICADO_ID,VETERINARIO_CALIFICADO_ID,DESCRIPCION) VALUES(143,'Dios','2021-09-07',3 ,null,100,'A mi perro le quedo doliendo la pata despues de la inyeccion');

INSERT INTO SERVICIO_ENTITY_PRESTADORES (SERVICIOS_OFRECIDOS_ID, PRESTADORES_ID) VALUES (100,100);
INSERT INTO SERVICIO_ENTITY_PRESTADORES (SERVICIOS_OFRECIDOS_ID, PRESTADORES_ID) VALUES (100,101);
INSERT INTO CITA_ENTITY_SERVICIOS_TOMADOS (CITAS_ID, SERVICIOS_TOMADOS_ID) VALUES (100,100);
