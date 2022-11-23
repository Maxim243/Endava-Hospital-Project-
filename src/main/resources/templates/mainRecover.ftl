<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>


<@c.page>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
  <strong>This time is occupied</strong>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>

</div>

<table class= "table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Doctor</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
      <th scope="col">Problem</th>
    </tr>
  </thead>
  <tbody>
    <#list records as record>
    <tr>
      <th scope="row">${record?index+1}</th>
      <td>${record.doctor}</td>
      <td>${record.date}</td>
      <td>${record.time}</td>
      <td>${record.problem}</td>
    </tr>
    </#list>
  </tbody>
</table>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add Appoinment
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">

            <div class="form-group row col-6">
                <label class="col-auto col-form-label">Select Date: </label>
                <div class="col-md-4">
                    <input type="date" class="form-control" name="date"/>
                </div>
                 <label class="col-auto col-form-label">Select a time:</label>
                 <input type="time" name="time">
            </div>

            <div class="form-group">
                <select name="doctorId" class="form-select" aria-label="Default select example">
                 <#list doctors as doctor>
                  <option name="doctorr" value="${doctor.id}">${doctor}</option>
                 </#list>
                </select>
            </div>

            <div class="form-group">
                <input type="text" class="form-control" name="problem" placeholder="Describe your problem">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-outline-success">Submit</button>
            </div>

          </div>
        </form>
    </div>
</div>
</@c.page>