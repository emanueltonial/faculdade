/* Cursor implicito */
create or replace procedure classifica_emp is
begin
    for emp_record in (
        select ename, sal
        from emp
        )
    loop
        if emp_record.sal > 2500 then
            dbms_output.put_line ('O funcionario ' || emp_record.ename || ' tem salario alto');
        else 
            dbms_output.put_line('O funcionario ' || emp_record.ename || ' tem salario normal');

        end if;
    end loop;
end classifica_emp;
/

/* Cursor explicito */
create or replace procedure classifica_emp as 
    cursor emp_cursor is
        select ename, sal
        from emp;
begin
    for emp_record in emp_cursor
    loop
        if emp_record.sal > 2500 then
            DBMS_OUTPUT.PUT_LINE ('O funcionario ' || emp_record.ename || ' tem salario alto');
        else 
            DBMS_OUTPUT.PUT_LINE ('O funcionario ' || emp_record.ename || ' tem salario normal');
        end if;
    end loop;
end;
/

/* ######################## */

/* Cursor explicito */
create or replace procedure classifica_emp as 
    cursor emp_cursor is
        select ename, sal
        from emp;
begin
    for emp_record in emp_cursor
    loop
        if emp_record.sal > 2500 then
            DBMS_OUTPUT.PUT_LINE ('O funcionario ' || emp_record.ename || ' tem salario alto');
        else 
            DBMS_OUTPUT.PUT_LINE ('O funcionario ' || emp_record.ename || ' tem salario normal');
        end if;
    end loop;
end;
/

/* ######################## */
/* Cursor explicito */
create or replace procedure classifica_emp_2 as
declare 
    cursor emp_cursor is
        select ename, sal
        from emp;
begin
    for emp_record in emp_cursor
    loop
        if emp_record.sal > 2500 then
            DBMS_OUTPUT.PUT_LINE ('O funcionario ' || emp_record.ename || ' tem salario alto');
        elsif emp_record.sal between 1000 and 2500 then
            DBMS_OUTPUT.PUT_LINE ('O funcionario ' || emp_record.ename || ' tem salario medio');
        else 
            DBMS_OUTPUT.PUT_LINE ('O funcionario ' || emp_record.ename || ' tem salario baixo');
        end if;
    end loop;
end;
/

/* ######################## */
/*Versão emanuel*/ 
create or replace procedure aumenta_salario(p_valor_salario emp.sal%type) as 

    cursor emp_cursor is
        select ename, sal
        from emp;
    
    sal_antigo emp.sal%type;
begin
    for emp_record in emp_cursor
    loop
        if emp_record.sal > p_valor_salario then
            update emp
            set sal = sal * 1.1
            where ename = emp_record.ename;
            DBMS_OUTPUT.PUT_LINE('Funcionario: ' || emp_record.ename || ' Salario: ' || emp_record.sal || ' Salario antigo:' || )
        end if;
    end loop;
end;
/
/*Versão André*/
create or replace procedure aumenta_salario(p_valor_salario emp.sal%type) as 

    cursor emp_cursor is
        select *
        from emp
        where sal < p_valor_salario;
    
    v_sal_novo  emp.sal%type;
begin
    for emp_record in emp_cursor
    loop
        v_sal_novo := emp_record.sal * 1.1
        update empt
        set sal = v_sal_novo
        where empno = emp_record.empno;
        
        dbms_output.put_line('Codigo: ' || emp_record.empno ||'Nome:' || emp_record.ename || ' Salario antigo: ' || emp_record.sal || 'Salario novo: ' || v_sal_novo);
    end loop;
end;
/
/*chamando a procedure*/
exec aumenta_salario(2000)

/* ######################## */
create or replace procedure aumenta_salario(p_valor_salario emp.sal%type, p_porcentagem_aumento emp.sal%type) as 

    cursor emp_cursor is
        select *
        from emp
        where sal < p_valor_salario;
    
    v_sal_novo  emp.sal%type;
begin
    for emp_record in emp_cursor
    loop
        v_sal_novo := emp_record.sal * p_porcentagem_aumento
        update emp
        set sal = v_sal_novo
        where empno = emp_record.empno;
        
        dbms_output.put_line('Codigo: ' || emp_record.empno ||'Nome:' || emp_record.ename || ' Salario antigo: ' || emp_record.sal || 'Salario novo: ' || v_sal_novo);
    end loop;
end;
/
/*chamando a procedure*/
exec aumenta_salario(2000, 1.5)

