import pymysql as my

def asteriscos(frase):
  frase=str(frase)
  s=len(frase)
  a=[]
  final=[]
  if s>3:
    sep=frase[0]+frase[1]+frase[2]
    a.append((s-3)*"*")
    a=list(a)
    a=a[0]
    final=sep+a
  return (final)

def restringido(id):
  cursor= connection.cursor()
  sql="select idCliente,nombre,sexo,segmento,cuenta from Cliente where idCliente ='%s';" % id
  cursor.execute(sql)
  datos = cursor.fetchall()
  return(datos)

def Validador(id,permiso):
  cursor= connection.cursor()
  sql="select apellidoPaterno, apellidoMaterno, fechaNacimiento, nacionalidad, rfc, tipoID, numeroID, email, TDD from Cliente where idCliente ='%s';" % id
  cursor.execute(sql)
  datos = cursor.fetchall()
  conv_datos=[]
  if permiso==0:
    for i in range(len(datos[0])):
      conv_datos.append(asteriscos(datos[0][i]))
  elif permiso==1: 
    conv_datos=datos
  return(conv_datos)    

try:
    connection= my.connect(
        host="localhost",
        user="root",
        password="118Schwester118",
        db="BBVA_test")
    try: 
          f=0
          while f<3:
            id  = input('Ingresa el ID del cliente')
            usuario = input('Usuario del asesor')
            contrasena = input('Contraseña del asesor')
            cursor= connection.cursor()
            sql="select perfil from Asesores where usuario ='%s' and auth='%s';" % (usuario, contrasena)
            cursor.execute(sql)
            myconsulta = cursor.fetchall()
            if not myconsulta:
                print("No se encontro sus datos de identificacion, favor de corregir las credenciales ingresadas")
                f+=1
            else:    
              myconsulta=myconsulta[0][0]
              if myconsulta=='Restringido':
                print("Entrando a la pagina con permiso Restringido")
                datos2=restringido(id)
                print("ID CLiente:", datos2[0][0],"Nombre:", datos2[0][1], "Sexo:", datos2[0][2], "Segmento:", datos2[0][3], "Cuenta:", datos2[0][4])

              elif myconsulta=='Validador':
                print("Entrando a la pagina con permiso Validador")
                datos2=restringido(id)
                print("ID CLiente:", datos2[0][0],"Nombre:", datos2[0][1], "Sexo:", datos2[0][2], "Segmento:", datos2[0][3], "Cuenta:", datos2[0][4])
                conv_datos=Validador(id, 0)
                print(conv_datos[0])
                print("Apellido Paterno:", conv_datos[0],"Apellido Materno:", conv_datos[1], "Fecha de nacimiento:", conv_datos[2], "Nacionalidad:", conv_datos[3], "RFC:", conv_datos[4], "TipoID:", conv_datos[5], "NumeroID:", conv_datos[6], "Email:", conv_datos[7],"TDD:", conv_datos[8])
                ##elementos_null(datos2,conv_datos)
              elif myconsulta=='Manager':
                print("Entrando a la pagina con permiso Manager")
                datos2=restringido(id)
                print(datos2)
                print("ID CLiente:", datos2[0][0],"Nombre:", datos2[0][1], "Sexo:", datos2[0][2], "Segmento:", datos2[0][3], "Cuenta:", datos2[0][4])
                conv_datos=Validador(id, 1)
                print("Apellido Paterno:", conv_datos[0][0],"Apellido Materno:", conv_datos[0][1], "Fecha de nacimiento:", conv_datos[0][2], "Nacionalidad:", conv_datos[0][3], "RFC:", conv_datos[0][4], "TipoID:", conv_datos[0][5], "NumeroID:", conv_datos[0][6], "Email:", conv_datos[0][7],"TDD:", conv_datos[0][8])
              break
            if f==3: 
              print("Intentos agotados, saliendo de la app")

    except my.Error as error:
          print("Error al iniciar consulta base de datos")

except my.Error as error:
    print("Fallo en la conexion de base de datos".format(error))


