const table = document.getElementById("table");
const RegisterStudentForm = document.getElementById('register-student-form');
const UpdateStudentForm = document.getElementById('update-student-form');
const GetOneStudent = document.getElementById('Get-one-student-form');
const tableBody = document.querySelector('table tbody');
const DeleteStudent= document.getElementById("Update-student-form");
const user_ASCII= localStorage.getItem("password");

document.getElementById("Get-student").addEventListener("click", (event) => {
  RegisterStudentForm.style.display = "none";
  DeleteStudent.style.display="none";
  UpdateStudentForm.style.display = "none";
  GetOneStudent.style.display = "none";
  table.style.display = "block";
 
  tableBody.innerHTML = '';
  let requestConfiq = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      'Authorization': "Basic " + user_ASCII
    }
  }
  fetch("http://localhost:8080/api/teachers", requestConfiq).then((response) => response.json())
    .then((paResponse) => {



      paResponse.forEach(item => {
        const row = document.createElement('tr');
        const nameCell = document.createElement('td');
        const idCell = document.createElement('td');
        const emailCell = document.createElement('td');

        nameCell.textContent = item.name;
        idCell.textContent = item.id;
        emailCell.textContent = item.email;

        row.appendChild(nameCell);
        row.appendChild(idCell);
        row.appendChild(emailCell);

        tableBody.appendChild(row);
      });


    }).catch(()=>   Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!'
    }))
})
document.getElementById("Register-student").addEventListener("click", (event) => {
  table.style.display = "none";
  DeleteStudent.style.display="none";
  GetOneStudent.style.display = "none";
  UpdateStudentForm.style.display = "none";
  RegisterStudentForm.style.display = "block";})
  document.getElementById("register-student-form").addEventListener("submit", (event) => {
    event.preventDefault();
    let studentName = document.getElementById("name").value;
    let studentEmail = document.getElementById("email").value;
    let studentImage = document.getElementById("image").files[0];

    let newStudent = {
      name: studentName,
      email: studentEmail
    }
    let requestConfiq = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        'Authorization': "Basic " + user_ASCII
      },
      body: JSON.stringify(newStudent)
    
    }
    fetch("http://localhost:8080/api/teachers", requestConfiq).then(response => {
      if (response.ok){
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'The teacher has been registered',
          showConfirmButton: false,
          timer: 1500
        })
        studentName="";
        studentEmail="";
        document.getElementById("name").value="";
        document.getElementById("email").value="";
      }
      else{
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong!'
        })
      }
    }).catch(()=>   Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!'
    }))
    
  })
document.getElementById("Get-one-student").addEventListener("click", () => {
  table.style.display = "none";
  DeleteStudent.style.display="none";
  RegisterStudentForm.style.display = "none";
  UpdateStudentForm.style.display = "none";
  GetOneStudent.style.display = "block";
});
document.getElementById("submit-student").addEventListener("click", ()=>{
  tableBody.innerHTML = '';
  let id = document.getElementById("idStudent").value;
  table.style.display = "block";
  const getStudent = (id) => {
    reqConfiq = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          'Authorization': "Basic " + user_ASCII
        }
    }
    url = `http://localhost:8080/api/teachers/${id}`
    fetch(url, reqConfiq)
        .then((response) => { return response.json(); })
        .then((parsedRResponse) => {  
          const tableBody = document.querySelector('table tbody');
          const row = document.createElement('tr');
          const nameCell = document.createElement('td');
          const idCell = document.createElement('td');
          const emailCell = document.createElement('td');
    
            nameCell.textContent = parsedRResponse.name;
            idCell.textContent = parsedRResponse.id;
            emailCell.textContent = parsedRResponse.email;
    
            row.appendChild(nameCell);
            row.appendChild(idCell);
            row.appendChild(emailCell);
            tableBody.appendChild(row);
  
         }).catch(()=>   Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong!'
        }));
            
          
        }

getStudent(id);
      }
)

document.getElementById("Update-student").addEventListener("click", () => {
  table.style.display = "none";
  RegisterStudentForm.style.display = "none";
  UpdateStudentForm.style.display = "block";
  GetOneStudent.style.display = "none";
  DeleteStudent.style.display="none";
 
  document.getElementById("update-student-form").addEventListener("submit", (event) => {
    event.preventDefault();
    let id= document.getElementById("id-to-update").value;
    let studentName = document.getElementById("name2").value;
    let studentEmail = document.getElementById("email2").value;
    let newStudent = {
      name: studentName,
      email: studentEmail
    } 
    let requestConfiq = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        'Authorization': "Basic " + user_ASCII
      },
      body: JSON.stringify(newStudent)
    }
    url = `http://localhost:8080/api/teachers/${id}`
    fetch(url, requestConfiq).then(response => {
      if (response.ok){
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'The teacher has been updated',
          showConfirmButton: false,
          timer: 1500
        })

        document.getElementById("id-to-update").value="";
        document.getElementById("name").value="";
        document.getElementById("email").value="";
      }
      else{
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong!'
        })
      }
    })
    
    
    
    .catch(()=>   Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!'
    }));
    
});

})

document.getElementById("Delete-student").addEventListener("click", ()=>{
  table.style.display = "none";
  RegisterStudentForm.style.display = "none";
  UpdateStudentForm.style.display = "none";
  GetOneStudent.style.display = "none";
  DeleteStudent.style.display="block";
  
})
document.getElementById("submit-student-delete").addEventListener("click", ()=>{
tableBody.innerHTML = '';
let id = document.getElementById("idStudentDelete").value;
  const deleteStudent = (id) => {
    reqConfiq = {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          'Authorization': "Basic " + user_ASCII
        }
    }
    url = `http://localhost:8080/api/teachers/${id}`
    
        Swal.fire({
          title: 'Are you sure?',
          text: "You won't be able to revert this!",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
          if (result.isConfirmed) {
            fetch(url, reqConfiq).then(response => {
              if (response.ok){
                Swal.fire(
                  'Deleted!',
                  'The teacher has been deleted.',
                  'success'
                )
            }}
            );
           
          }
        })
        document.getElementById("idStudentDelete").value="";
      }
      
          
        deleteStudent(id);
    }
      );



    
  
