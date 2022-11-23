<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<table class="table table-bordered">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Doctor</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
      <th scope="col">Problem</th>
      <th scope="col">Action</th>
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
      <td> <a href="/record/${record.id}">
<input type="submit" class="btn btn-outline-danger" value="Delete"/></a>
</td>
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
                <label class="col-auto col-form-label"> Select Date:</label>
                <div class="col-md-4">
                    <input type="date" class="form-control" name="date"/>
                </div>
                 <label class="col-auto col-form-label"> Select time:</label>
                 <input type="time" name="time">
            </div>

  <div class="form-group">
    <h3> <label for="exampleFormControlSelect2">Please select your Doctor</label> </h3>
    <select name="doctorId" class="form-control" id="exampleFormControlSelect2">
                 <#list doctors as doctor>
                  <option name="doctor" value="${doctor.id}">${doctor}</option>
                 </#list>
    </select>
  </div>

            <div class="form-group">
                <input type="text" class="form-control" name="problem" placeholder="Describe your problem">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" href="/main" class="btn btn-outline-success">Submit</button>
            </div>

          </div>
        </form>
    </div>
</div>
</@c.page>


