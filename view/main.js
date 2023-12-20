const url = "http://localhost:8080/task/user/3";

function hideLoader() {
  document.getElementById("loading").style.display = "none";
}

function showData(tasks) {
  let tab = `<task>
    <th scope"col">#</th>

    <th scope"col">Description</th>
    <th scope"col">Username</th>
    <th scope"col">User Id</th>

    </thead>
    `;

  for (const task of tasks) {
    tab += `
        <tr>
            <th scope"row">${task.id}</th>
            <th scope"row">${task.description}</th>
            <th scope"row">${task.user.username}</th>
            <th scope"row">${task.user.id}</th>
        </tr>
        `;
  }

  document.getElementById("tasks").innerHTML = tab;
}

async function getAPI(url) {
  const response = await fetch(url, { method: "GET" });

  var data = await response.json();
  console.log(data);
  if (response) hideLoader();
  showData(data);
}

getAPI(url);
