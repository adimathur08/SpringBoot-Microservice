function removeUser(userId) { // <-- Added closing parenthesis here
        var api = "http://localhost:8080/user/remove/" + userId;
        console.log("trying to hit " + api);

        fetch(api, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                console.log("unable to delete");
            } else {
                console.log("Deleted user");
                // Handle successful deletion (e.g., redirect to user list)
            }
        })
        .catch(error => {
            console.error("Error deleting user:", error);
            // Handle network or other errors
        });
    }





function registerUser() {
  const name = document.getElementById('name').value;
  const age = document.getElementById('age').value;
  const address = document.getElementById('address').value;
  const email = document.getElementById('email').value;

  const data = {
    name,
    age,
    address,
    email,
  };

  console.log("trying to save this data : " + JSON.stringify(data))

  fetch('http://localhost:8080/user/save', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  })
  .then(response => {
    if (response.ok) {
      // Handle successful response
      console.log("User saved with message : " + JSON.stringify(response))
      document.getElementById('response').textContent = 'User registered successfully!';
    } else {
      // Handle error response
      console.error('Error registering user:', response.statusText);
      response.text().then(text => console.error(text));
      document.getElementById('response').textContent = 'Registration failed: ' + response.statusText;
    }
  })
  .catch(error => {
    // Handle network or other errors
    console.error('Error fetching data:', error);
    document.getElementById('response').textContent = 'An error occurred. Please try again later.';
  });
}