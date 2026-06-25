set serverout on
DECLARE
v_ename emp.ename%type;
v_deptno emp.deptno%type;
BEGIN
SELECT ename, deptno INTO v_ename, v_deptno
FROM emp
WHERE job = '&1';
DBMS_OUTPUT.PUT_LINE ('Funcionario ' || v_ename || ' trabalha no depto ' || v_deptno);
END;
/
---

declare
    cursor c_emp is 
        select ename, sal
        from emp
        order by 2 desc;

        v_max_sal emp.sal%type
        v_dif number;
begin
    select max(sal)
    into v_max_sal
    from emp;

    for r_emp in c_emp
    loop   
        begin
            v_dif := v_max_sal / r_emp.sal;
            dbms_output.put_line('Nome:' ||r_emp.ename|| 'Salario:' ||r_emp.sal|| 'Proporcao:' to_char(v_dif,99.999));
            exception
                when zero_divide then
                    DBMS_OUTPUT.PUT_LINE('Nome:' ||r_emp.ename|| 'Salario:' ||r_emp.sal|| 'Proporcao: Salario zerado!');
        end;    
    end loop;
end;
/

---
declare
    cursor c_emp is 
        select ename, sal
        from emp
        order by 2 desc;

        v_max_sal emp.sal%type
        v_dif number;
begin
    select max(sal)
    into v_max_sal
    from emp;

    for r_emp in c_emp
    loop   
        begin
            v_dif := v_max_sal / r_emp.sal;
            dbms_output.put_line('Nome:' ||r_emp.ename|| 'Salario:' ||r_emp.sal|| 'Proporcao:' to_char(v_dif,99.999));
            exception
                when others then
                    DBMS_OUTPUT.PUT_LINE('Nome:' ||r_emp.ename|| 'Proporcao: Salario zerado! ' || sqlerrm);
        end;    
    end loop;
end;
/