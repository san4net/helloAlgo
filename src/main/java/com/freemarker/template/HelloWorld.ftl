<html>
<head>
  <title>${title}</title>
</head>
<body>
  <h1>${title}</h1>

  <p>${exampleObject.developer} working with ${exampleObject.company}</p>

  <ul>
    <#list systems as system>
      <li>${system_index + 1}. ${system.developer} from ${system.company}</li>
    </#list>
  </ul>

</body>
</html> 