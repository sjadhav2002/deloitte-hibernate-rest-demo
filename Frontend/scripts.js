

const getAllEmployees = async () => {
    const resp = await fetch("http://localhost:8090/deloitte-hybernate-rest-demo/employees")
    const response = await resp.json();
    return response;
}


const getEmployeebyId = async (id) => {
    const resp = await fetch("http://localhost:8090/deloitte-hybernate-rest-demo/employees/"+id)
    const response = await resp.json();
    return response;
}

const updateEmployee = async (event) => {
    event.preventDefault();  

    const form = document.getElementById('updateemployeeForm');
    const formData = new FormData(form);

    const employee = {
        firstName: formData.get('firstName'),
        salary: parseFloat(formData.get('salary')),
        department: formData.get('departmentId')
    };


    try {
        const response = await fetch('http://localhost:8090/deloitte-hybernate-rest-demo/employees/'+formData.get('id'), { 
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(employee)
        });

        const resp = await response.json();
        if (resp.msg === "Failed"){
            alert(resp.reason);
        }
        else{
            alert('Employee Updated successfully!');
            window.location.reload();
        }
    } catch (error) {
        console.error('Request failed', error);
        alert('Request failed');
    }
};

const deleteEmployee = async (id) => {
    const resp = await fetch('http://localhost:8090/deloitte-hybernate-rest-demo/employees/'+id, { 
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const response = await resp.json();
    if (response.msg === "Failed"){
        alert(response.reason);
    }
    else{
        alert('Employee Deleted successfully!');
        window.location.reload();
    }

    return response;
}

const addEmployee = async (event) => {
    event.preventDefault();  

    const form = document.getElementById('newemployeeForm');
    const formData = new FormData(form);

    const employee = {
        firstName: formData.get('firstName'),
        salary: parseFloat(formData.get('salary')),
        department: formData.get('departmentId')
    };


    try {
        const response = await fetch('http://localhost:8090/deloitte-hybernate-rest-demo/employees', { 
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(employee)
        });
        const resp = await response.json();
        if (resp.msg === "Failed"){
            alert(resp.reason);
        }
        else{
            alert('Employee Created successfully!');
            window.location.reload();
        }
    } catch (error) {
        console.error('Request failed', error);
        alert('Request failed');
    }
};






const getAllDepartments = async () => {
    const resp = await fetch("http://localhost:8090/deloitte-hybernate-rest-demo/departments")
    const response = await resp.json();
    return response;
}


const getDepartmentbyId = async (id) => {
    const resp = await fetch("http://localhost:8090/deloitte-hybernate-rest-demo/departments/"+id)
    const response = await resp.json();
    return response;
}

const updateDepartment = async (event) => {
    event.preventDefault();  

    const form = document.getElementById('updatedepartmentForm');
    const formData = new FormData(form);

    const departments = {
        name: formData.get('name'),
        location: formData.get('location')
    };


    try {
        const response = await fetch('http://localhost:8090/deloitte-hybernate-rest-demo/departments/'+formData.get('id'), { 
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(departments)
        });

        const resp = await response.json();
        if (resp.msg === "Failed"){
            alert(resp.reason);
        }
        else{
            alert('Department Updated successfully!');
            window.location.reload();
        }
    } catch (error) {
        console.error('Request failed', error);
        alert('Request failed');
    }
};

const deleteDepartment = async (id) => {
    const resp = await fetch('http://localhost:8090/deloitte-hybernate-rest-demo/departments/'+id, { 
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const response = await resp.json();
    if (response.msg === "Failed"){
        alert(response.reason);
    }
    else{
        alert('Department Deleted successfully!');
        window.location.reload();
    }

    return response;
}

const addDepartment = async (event) => {
    event.preventDefault();  

    const form = document.getElementById('newdepartmentForm');
    const formData = new FormData(form);

    const departments = {
        name: formData.get('name'),
        location: formData.get('location')
    };


    try {
        const response = await fetch('http://localhost:8090/deloitte-hybernate-rest-demo/departments', { 
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(departments)
        });
        const resp = await response.json();
        if (resp.msg === "Failed"){
            alert(resp.reason);
        }
        else{
            alert('Department Created successfully!');
            window.location.reload();
        }
    } catch (error) {
        console.error('Request failed', error);
        alert('Request failed');
    }
};