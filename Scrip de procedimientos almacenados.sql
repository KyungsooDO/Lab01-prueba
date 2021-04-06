
-- Procedimiento para clase avion

delimiter $$
create procedure agregar_avion (in a_id_avion varchar(50),in a_id_tipo_avion varchar(50))
begin
insert into avion (idAvion,tipoAvion)
values (a_id_avion,a_id_tipo_avion);
end$$
delimiter $$

call agregar_avion('7','001');

delimiter $$
create procedure actualizar_avion (in a_id_avion varchar(50),in a_id_tipo_avion varchar(50))
begin
update Avion set tipoAvion=a_id_tipo_avion
where idAvion=a_id_avion;
end$$
delimiter $$

call actualizar_avion('7','004');

delimiter $$
create procedure eliminar_avion (in a_id_avion varchar(50))
begin
delete from Avion
where idAvion=a_id_avion;
end$$
delimiter $$

call eliminar_avion('7');

delimiter $$
create procedure obtener_avion (in a_id_avion varchar(50))
begin
select* from Avion
where idAvion=a_id_avion;
end$$
delimiter $$

call obtener_avion('7');

delimiter $$
create procedure delimiter $$
create procedure listar_avion ()
begin
select* from Avion;
end$$
delimiter $$

call listar_avion();

delimiter $$
create procedure delimiter $$
create procedure buscar_avion (in a_id_avion varchar(50),in a_id_tipo_avion varchar(50))
begin
select* from Avion
where idAvion LIKE a_id_avion and tipoAvion LIKE a_id_tipo_avion;
end$$
delimiter $$

call buscar_avion('0','');

-- Procedimientos para la clase ciudad

delimiter $$
create procedure eliminar_ciudad (in c_id_ciudad varchar(50))
begin
delete from ciudad
where idCiudad=c_id_ciudad;
end$$
delimiter $$

call eliminar_ciudad('02');

delimiter $$
create procedure agregar_ciudad (in c_id_ciudad varchar(50),in c_pais varchar(50),in c_nombre varchar(50))
begin
insert into ciudad (idCiudad,pais,nombre)
values (c_id_ciudad,c_pais,c_nombre);
end$$
delimiter $$

call agregar_ciudad('7','001');

delimiter $$
create procedure actualizar_ciudad (in c_pais varchar(50),in c_nombre varchar(50),in c_id_ciudad varchar(50))
begin
update Avion set pais = c_pais,nombre = c_nombre
where idCiudad = c_id_ciudad;
end$$
delimiter $$

call actualizar_ciudad('7','004');

delimiter $$
create procedure obtener_ciudad (in c_id_ciudad varchar(50))
begin
select* from ciudad
where idCiudad=c_id_ciudad;
end$$
delimiter $$

call obtener_ciudad('7');

delimiter $$
create procedure delimiter $$
create procedure listar_ciudad ()
begin
select* from Ciudad;
end$$
delimiter $$

call listar_ciudad();


delimiter $$
create procedure delimiter $$
create procedure buscar_ciudad (in c_id_ciudad varchar(50),in c_nombre varchar(50))
begin
select* from ciudad
where idCiudad LIKE c_id_ciudad and nombre LIKE c_nombre;
end$$
delimiter $$

call buscar_avion('0','');


-- Procedimientos para la clase Fecha Vuelo

delimiter $$
create procedure agregar_fechavuelo (in f_idFechaVuelo varchar(50),in f_vuelo varchar(50),in f_fecha date,in f_disponibles int,in f_precio double)
begin
insert into fechavuelo (idFechaVuelo,vuelo,fecha,disponibles,precio)
values (f_idFechaVuelo,f_vuelo,f_fecha,f_disponibles,f_precio);
end$$
delimiter $$


delimiter $$
create procedure actualizar_fechavuelo (in f_vuelo varchar(50),in f_fecha date,in f_disponibles int,in f_precio double,in f_idFechaVuelo varchar(50))
begin
update fechavuelo set vuelo = f_vuelo,fecha = f_fecha,disponibles = f_disponibles, precio = f_precio
where idFechaVuelo = f_idFechaVuelo;
end$$
delimiter $$

delimiter $$
create procedure eliminar_fechavuelo (in f_idFechaVuelo varchar(50))
begin
delete from fechavuelo
where idFechaVuelo = f_idFechaVuelo;
end$$
delimiter $$

delimiter $$
create procedure obtener_fechavuelo (in f_idFechaVuelo varchar(50))
begin
select* from fechavuelo
where idFechaVuelo = f_idFechaVuelo;
end$$
delimiter $$

delimiter $$
create procedure delimiter $$
create procedure listar_fechavuelo ()
begin
select* from fechavuelo;
end$$
delimiter $$

-- Procedimientos para la clase Forma Pago

delimiter $$
create procedure eliminar_formapago (in f_id_formapago varchar(50))
begin
delete from formapago
where idFormaPago=f_id_formapago;
end$$
delimiter $$

delimiter $$
create procedure agregar_formapago (in f_id_formapago varchar(50),in f_nombre varchar(50))
begin
insert into formapago (idFormaPago,nombre)
values (f_id_formapago,f_nombre);
end$$
delimiter $$

delimiter $$
create procedure actualizar_formapago (in f_nombre varchar(50),in f_id_formapago varchar(50))
begin
update formapago set nombre = f_nombre
where idFormaPago = f_id_formapago;
end$$
delimiter $$

delimiter $$
create procedure obtener_formapago (in f_id_formapago varchar(50))
begin
select* from formapago
where idFormaPago = f_id_formapago;
end$$
delimiter $$

delimiter $$
create procedure listar_formapago ()
begin
select* from formapago;
end$$
delimiter $$

-- Procedimientos para la clase pais

delimiter $$
create procedure eliminar_pais (in p_idPais varchar(50))
begin
delete from pais
where idPais=p_idPais;
end$$
delimiter $$

delimiter $$
create procedure agregar_pais (in p_idPais varchar(50),in p_nombre varchar(50))
begin
insert into pais (idPais,nombre)
values (p_idPais,p_nombre);
end$$
delimiter $$

delimiter $$
create procedure actualizar_pais (in p_nombre varchar(50),in p_idPais varchar(50))
begin
update pais set nombre = p_nombre
where idPais = p_idPais;
end$$
delimiter $$

delimiter $$
create procedure obtener_pais (in p_idPais varchar(50))
begin
select* from pais
where idPais = p_idPais;
end$$
delimiter $$

delimiter $$
create procedure listar_pais ()
begin
select* from pais;
end$$
delimiter $$

-- Prodedimientos almacenados para la clase país.


delimiter $$
create procedure agregar_reserva (in r_usuario varchar(50),in r_fechavuelo varchar(50),in r_formapago varchar(50))
begin
insert into reserva (fechaVuelo,formaPago,usuario)
values (r_fechavuelo,r_formapago,r_usuario);
end$$
delimiter $$

delimiter $$
create procedure actualizar_reserva (in r_usuario varchar(50),in r_fechavuelo varchar(50),in r_formapago varchar(50),in r_idreserva int)
begin
update reserva set usuario = r_usuario, fechaVuelo = r_fechavuelo, formaPago = r_formapago
where idReserva = r_idreserva;
end$$
delimiter $$

delimiter $$
create procedure eliminar_reserva (in r_idReserva int)
begin
delete from reserva
where idReserva=r_idReserva;
end$$
delimiter $$

delimiter $$
create procedure obtener_reserva (in r_idReserva int)
begin
select* from reserva
where idReserva=r_idReserva;
end$$
delimiter $$

delimiter $$
create procedure listar_reserva ()
begin
select* from reserva;
end$$
delimiter $$

delimiter $$
create procedure listar_reservas_por_usuario (in r_usuario varchar(50))
begin
select* from reserva
where usuario = r_usuario;
end$$
delimiter $$

delimiter $$
create procedure listar_tiquetes_por_fechavuelo (in r_fechaVuelo varchar(50))
begin
select* from reserva
where fechaVuelo = r_fechaVuelo;
end$$
delimiter $$

-- Procedimientos para la clase tipo de avion

delimiter $$
create procedure agregar_tipoavion (in t_idTipoAvion varchar(50),in t_mondelo varchar(50),in t_cantidadAsientos int, t_cantidadFilas int, t_cantidadColumnas int)
begin
insert into tipoavion (idTipoAvion,mondelo,cantidadAsientos,cantidadFilas,cantidadColumnas)
values (t_idTipoAvion,t_mondelo,t_cantidadAsientos,t_cantidadFilas,t_cantidadColumnas);
end$$
delimiter $$

delimiter $$
create procedure actualizar_tipoavion (in t_mondelo varchar(50),in t_cantidadAsientos int, t_cantidadFilas int, t_cantidadColumnas int,in t_idTipoAvion varchar(50))
begin
update tipoavion set mondelo = t_mondelo, cantidadAsientos = t_cantidadAsientos, cantidadFilas = t_cantidadFilas, cantidadColumnas = t_cantidadColumnas
where idTipoAvion = '%%t_mondelo';
end$$
delimiter $$

delimiter $$
create procedure eliminar_tipoavion (in t_idTipoAvion varchar(50))
begin
delete from tipoavion
where idTipoAvion=t_idTipoAvion;
end$$
delimiter $$

delimiter $$
create procedure obtener_tipoavion (in t_idTipoAvion varchar(50))
begin
select* from tipoavion
where idTipoAvion=t_idTipoAvion;
end$$
delimiter $$

delimiter $$
create procedure listar_tipoavion ()
begin
select* from tipoavion;
end$$
delimiter $$

-- Procedimientos para la clase tiquete

delimiter $$
create procedure agregar_tiquete (in t_asiento varchar(50),in t_reserva int)
begin
insert into tiquete (asiento,reserva)
values (t_asiento,reserva);
end$$
delimiter $$

delimiter $$
create procedure actualizar_tiquete (in t_reserva int,in t_asiento varchar(50), t_idTiquete int)
begin
update tiquete set reserva = t_reserva, asiento = t_asiento
where idTiquete = t_idTiquete;
end$$
delimiter $$

delimiter $$
create procedure eliminar_tiquete (in t_idTiquete int)
begin
delete from tiquete
where idTiquete=t_idTiquete;
end$$
delimiter $$

delimiter $$
create procedure obtener_tiquete (in t_idTiquete varchar(50))
begin
select* from tiquete
where idTiquete=t_idTiquete;
end$$
delimiter $$

delimiter $$
create procedure buscar_tiquete (in t_reserva int)
begin
select* from tiquete
where reserva=t_reserva;
end$$
delimiter $$

delimiter $$
create procedure listar_tiquete ()
begin
select* from tiquete;
end$$
delimiter $$

delimiter $$
create procedure buscar_tiquete_reserva (in t_reserva int)
begin
select* from tiquete
where reserva=t_reserva;
end$$
delimiter $$

-- Procedimientos para la clase usuario
delimiter $$
create procedure eliminar_usuario (in u_idUsuario varchar(20))
begin
delete from usuario
where idUsuario=u_idUsuario;
end$$
delimiter $$

delimiter $$
create procedure obtener_usuario (in u_idUsuario varchar(50))
begin
select* from usuario
where idUsuario=u_idUsuario;
end$$
delimiter $$

delimiter $$
create procedure listar_usuario ()
begin
select* from usuario;
end$$
delimiter $$

delimiter $$
create procedure agregar_usuario (

in u_idUsuario varchar(20),
in u_nombre varchar(20),
in u_contraseña varchar(20),
in u_apellido varchar(20),
in u_correoElectronico varchar(30),
in u_fechaNacimiento date,
in u_direccion varchar(30),
in u_telefonoTrabajo int,
in u_telefonoCelular int

)
begin
insert into usuario (
idUsuario,
nombre,
contraseña,
apellido,
correoElectronico,
fechaNacimiento,
direccion,
telefonoTrabajo,
telefonoCelular)
values (
u_idUsuario,
u_nombre,
u_contraseña,
u_apellido,
u_correoElectronico,
u_fechaNacimiento,
u_direccion,
u_telefonoTrabajo,
u_telefonoCelular);
end$$
delimiter $$


delimiter $$
create procedure actualizar_usuario (

in u_nombre varchar(20),
in u_contraseña varchar(20),
in u_apellido varchar(20),
in u_correoElectronico varchar(30),
in u_fechaNacimiento date,
in u_direccion varchar(30),
in u_telefonoTrabajo int,
in u_telefonoCelular int,
in u_idUsuario varchar(20)

)
begin
update usuario set 
nombre = u_nombre,
contraseña = u_contraseña,
apellido = u_apellido,
correoElectronico = u_correoElectronico,
fechaNacimiento = u_fechaNacimiento,
direccion = u_direccion,
telefonoTrabajo = u_telefonoTrabajo,
telefonoCelular = u_telefonoCelular
where idUsuario = u_idUsuario;
end$$
delimiter $$

-- Procedimientos para la clase vuelo

delimiter $$
create procedure agregar_vuelo (
in v_idVuelo varchar(20),
in v_dia varchar(30),
in v_hora time,
in v_avion varchar(10),
in v_origen varchar(10),
in v_destino varchar(10),
in v_duracion time
)
begin
insert into vuelo (idVuelo,dia,hora,avion,origen,destino,duracion)
values (v_idVuelo,v_dia,v_hora,v_avion,v_origen,v_destino,v_duracion);
end$$
delimiter $$


delimiter $$
create procedure actualizar_vuelo (

in v_dia varchar(30),
in v_hora time,
in v_avion varchar(10),
in v_origen varchar(10),
in v_destino varchar(10),
in v_duracion time,
in v_idVuelo varchar(20)

)
begin
update vuelo set 
dia = v_dia,
hora = v_hora,
avion = v_avion,
origen = v_origen,
destino = v_destino,
duracion = v_duracion
where  idVuelo = t_idTiquete;
end$$
delimiter $$

delimiter $$
create procedure obtener_vuelo (in u_idVuelo varchar(50))
begin
select* from vuelo
where idVuelo=u_idVuelo;
end$$
delimiter $$

delimiter $$
create procedure listar_vuelo ()
begin
select* from vuelo;
end$$
delimiter $$

delimiter $$
create procedure eliminar_vuelo (in u_idVuelo varchar(20))
begin
delete from vuelo
where idVuelo=u_idVuelo;
end$$
delimiter $$


