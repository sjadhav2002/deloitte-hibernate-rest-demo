

const getAllEmployees = async () => {
    const resp = await fetch("http://localhost:8090/deloitte-hybernate-rest-demo/employees")
    const response = await resp.json();
    const table = document.createElement("table");
    table.setAttribute("border", "1");

    const thead = table.createTHead();
    const headerRow = thead.insertRow();
    
    const headers = ["ID", "Name", "Department", "Salary"];
    headers.forEach(headerText => {
        const th = document.createElement("th");
        th.appendChild(document.createTextNode(headerText));
        headerRow.appendChild(th);
    });

    const tbody = document.createElement("tbody");
    response.forEach(employee => {
        const row = tbody.insertRow();

        const cellId = row.insertCell();
        const cellName = row.insertCell();
        const cellDepartment = row.insertCell();
        const cellSalary = row.insertCell();

        cellId.appendChild(document.createTextNode(employee.id));
        cellName.appendChild(document.createTextNode(employee.firstName));
        cellDepartment.appendChild(document.createTextNode(employee.department));
        cellSalary.appendChild(document.createTextNode(employee.salary));
    });

    table.appendChild(tbody);
    const allEmployeeDiv = document.getElementById("AllEmployeeTable");
    allEmployeeDiv.innerHTML = "";
    allEmployeeDiv.appendChild(table);
}


const getEmployeebyId = async (id) => {
    const resp = await fetch("http://localhost:8090/deloitte-hybernate-rest-demo/employees/"+id)
    const response = await resp.json();

    const card = document.createElement("div");
    card.setAttribute("class", "card mb-3");

    const cardHeader = document.createElement("div");
    cardHeader.setAttribute("class", "card-header");
    cardHeader.textContent = response.firstName; 

    const cardBody = document.createElement("div");
    cardBody.setAttribute("class", "card-body");

    const fields = ["ID", "Department", "Salary"];
    fields.forEach(field => {
        const p = document.createElement("p");
        p.setAttribute("class", "card-text");

        switch (field) {
            case "ID":
                p.textContent = `ID: ${response.id}`;
                break;
            case "Department":
                p.textContent = `Department: ${response.department}`;
                break;
            case "Salary":
                p.textContent = `Salary: ${response.salary}`;
                break;
        }
        cardBody.appendChild(p);
    });
    card.appendChild(cardHeader);
    card.appendChild(cardBody);

    const employeeByIdDiv = document.getElementById("EmployeebyID");
    employeeByIdDiv.innerHTML = "";
    employeeByIdDiv.appendChild(card);
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

    const table = document.createElement("table");
    table.setAttribute("border", "1");

    const thead = table.createTHead();
    const headerRow = thead.insertRow();
    
    const headers = ["ID", "Name", "Location"];
    headers.forEach(headerText => {
        const th = document.createElement("th");
        th.appendChild(document.createTextNode(headerText));
        headerRow.appendChild(th);
    });

    const tbody = document.createElement("tbody");
    response.forEach(department => {
        const row = tbody.insertRow();

        const cellId = row.insertCell();
        const cellName = row.insertCell();
        const cellLocation = row.insertCell();

        cellId.appendChild(document.createTextNode(department.id));
        cellName.appendChild(document.createTextNode(department.name));
        cellLocation.appendChild(document.createTextNode(department.location));
    });

    table.appendChild(tbody);
    const allEmployeeDiv = document.getElementById("AllDepartmentsTable");
    allEmployeeDiv.innerHTML = "";
    allEmployeeDiv.appendChild(table);
}


const getDepartmentbyId = async (id) => {
    const resp = await fetch("http://localhost:8090/deloitte-hybernate-rest-demo/departments/"+id)
    const response = await resp.json();

    const card = document.createElement("div");
    card.setAttribute("class", "card mb-3");

    const cardHeader = document.createElement("div");
    cardHeader.setAttribute("class", "card-header");
    cardHeader.textContent = response.name; 

    const cardBody = document.createElement("div");
    cardBody.setAttribute("class", "card-body");

    const fields = ["ID", "Location"];
    fields.forEach(field => {
        const p = document.createElement("p");
        p.setAttribute("class", "card-text");

        switch (field) {
            case "ID":
                p.textContent = `ID: ${response.id}`;
                break;
            case "Location":
                p.textContent = `Location: ${response.location}`;
                break;

        }
        cardBody.appendChild(p);
    });
    card.appendChild(cardHeader);
    card.appendChild(cardBody);

    const employeeByIdDiv = document.getElementById("DepartmentbyID");
    employeeByIdDiv.innerHTML = "";
    employeeByIdDiv.appendChild(card);
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