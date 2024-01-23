DROP FUNCTION obtenerinfoDepartamento

DELIMITER //

CREATE FUNCTION obtenerInfoDepartamento(depto INT) RETURNS VARCHAR(100)
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE numEmpleados INT;
    DECLARE salarioPromedio DECIMAL(10, 2);

    SELECT COUNT(emp_no), AVG(salario)
    INTO numEmpleados, salarioPromedio
    FROM empleado
    WHERE  dept_no = depto;

    RETURN CONCAT('Número de empleados: ', numEmpleados, ', Salario promedio: ', salarioPromedio);
END //

DELIMITER ;
SELECT obtenerInfoDepartamento(10);