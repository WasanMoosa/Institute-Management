const table = document.getElementById("table");
const RegisterStudentForm = document.getElementById('register-student-form');
const UpdateStudentForm = document.getElementById('update-student-form');
const GetOneStudent = document.getElementById('Get-one-student-form');
const tableBody = document.querySelector('table tbody');
const DeleteStudent= document.getElementById("delete-student-form");
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
  fetch("http://localhost:8080/api/students", requestConfiq).then((response) => response.json())
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

    let form = new FormData;
    form.append("name",studentName);
    form.append("email",studentEmail);
    if (studentImage != null){
    form.append("image",studentImage);
    }

    let newStudent = {
      name: studentName,
      email: studentEmail
    }
    let requestConfiq = {
      method: "POST",
      headers: {
        // "Content-Type": "application/json",
        'Authorization': "Basic " + user_ASCII
      },
      // body: JSON.stringify(newStudent)
      body: form
    }
    fetch("http://localhost:8080/api/students/withImage", requestConfiq).then(response => {
      if (response.ok){
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'The student has been registered',
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

  document.getElementById("student-info").style.display="none";
  table.style.display = "none";
  DeleteStudent.style.display="none";
  RegisterStudentForm.style.display = "none";
  UpdateStudentForm.style.display = "none";
  GetOneStudent.style.display = "block";

});

document.getElementById("submit-student").addEventListener("click", ()=>{
  document.getElementById("student-info").style.display="block";
  document.getElementById("name_s").textContent = " ";
  document.getElementById("id_s").textContent = " ";
  document.getElementById("email_s").textContent = " ";
  const t = document.getElementById("for-image-and-name");
  const imgElement = t.querySelector(".profile_img");

if (imgElement) {
  t.removeChild(imgElement);
}
  
  tableBody.innerHTML = '';
  let id = document.getElementById("idStudent").value;
  const getStudent = (id) => {
    reqConfiq = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          'Authorization': "Basic " + user_ASCII
        }
    }
    url = `http://localhost:8080/api/students/${id}`
    fetch(url, reqConfiq)
        .then((response) => { return response.json(); })
        .then((parsedRResponse) => {  
          const name = parsedRResponse.name;
          const studentID = parsedRResponse.id;
          const email = parsedRResponse.email;
          const imagePath= parsedRResponse.imagePath;
          console.log(parsedRResponse)
    
          
          // Display the student info
          document.getElementById("name_s").textContent = `Name: ${name}`;
          document.getElementById("id_s").innerHTML = `<strong>ID:</strong> ${studentID}`;
          document.getElementById("email_s").innerHTML = `<strong>Email:</strong> ${email}`;

          url = "http://localhost:8080"+imagePath;
          fetch(url, reqConfiq)
            .then((response) => { return response.blob(); })
            .then((blobResponse) => {  
             console.log("success");
             const imgElement = document.createElement("img");
            imgElement.src = URL.createObjectURL(blobResponse);
            
          imgElement.classList.add("profile_img")
          t.style.display="block";
          t.appendChild(imgElement);
             })
         })
        }
            
getStudent(id);
/////////////////////////////////////////////
reqConfiq = {
  method: "GET",
  headers: {
    "Content-Type": "application/json",
    'Authorization': "Basic " + user_ASCII
  }
}


   ///////////////////////////////////////////
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
    url = `http://localhost:8080/api/students/${id}`
    fetch(url, requestConfiq).then(response => {
      if (response.ok){
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'The student has been updated',
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
    url = `http://localhost:8080/api/students/${id}`
    
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
                  'The student has been deleted.',
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



    
  
