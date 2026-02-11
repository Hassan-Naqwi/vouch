<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vouch</title>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>

</head>

<body>

    <%@page import="java.util.ArrayList, myPack.UserInfo" %>
        <% String role=(String)session.getAttribute("role"); if(role==null ) { %>
            <jsp:forward page="/index.jsp" />
            <% } else if(role.equals("user") ) { %>
                <jsp:forward page="/userDashboard/Dashboard.jsp" />
                <% }%>
                    <div class="flex">
                        <aside class="border-r h-screen w-1/4">
                            <div class="flex pl-10 py-10 flex-col">
                                <% String name=(String)session.getAttribute("name"); String
                                    email=(String)session.getAttribute("email"); %>
                                    <h3 class="text-2xl font-bold">
                                        <%= name %>
                                    </h3>
                                    <h5 class="text-sm font-semibold">
                                        <%= email %>
                                            </h3>
                            </div>
                            <h1 class="text-2xl font-bold pl-10">
                                <%=role.toUpperCase()%> DASHBOARD
                            </h1>
                            <ul class="flex flex-col  gap-2 mt-10">
                                <li><a href="/vouch/controller?action=feed"
                                        class="p-5 pl-10 font-semibold block w-full bg-gray-300 hover:bg-gray-200 ">Feed</a>
                                </li>
                                <li><a href="/vouch/controller?action=showPendingPosts"
                                        class="p-5 pl-10 font-semibold block w-full bg-gray-300 hover:bg-gray-200 ">Pending
                                        Posts</a>
                                </li>
                                <li><a href="/vouch/controller?action=showUsers"
                                        class="p-5 pl-10 font-semibold block w-full bg-gray-400 hover:bg-gray-300 ">Active
                                        Users</a>
                                </li>
                            </ul>
                            <a href="/vouch/controller?action=logout"
                                class="px-5 py-2 text-white inline-block mt-10 ml-10 rounded-lg bg-red-600 hover:bg-red-500">Logout</a>
                        </aside>


                        <% ArrayList<UserInfo>users= (ArrayList<UserInfo>
                                )request.getAttribute("users");
                                if(users!=null){ %>
                                <section class="w-3/4 min-h-screen overflow-y-scroll">
                                    <div class="flex flex-col items-center">
                                        <h1 class="font-bold text-3xl py-10">Active Users</h1>
                                        <table
                                            class="table-auto border-collapse border-spacing-x-30 border-spacing-y-5 text-left">
                                            <thead>
                                                <th class="border px-6 py-2">Name</th>
                                                <th class="border px-6 py-2">Email</th>
                                                <th class="border px-6 py-2">Role</th>
                                                <th class="border px-6 py-2">Password</th>
                                                <th class="border px-6 py-2">Action</th>
                                            </thead>
                                            <% if(users.isEmpty()) {%>
                                                <tr>
                                                    <td colspan="5" class="text-center text-red-500 pt-5">No Active
                                                        User Found
                                                    </td>
                                                </tr>
                                                <% }else for(UserInfo user : users) { %>
                                                    <tr>
                                                        <td class="border px-6 py-2">
                                                            <%=user.getName()%>
                                                        </td>
                                                        <td class="border px-6 py-2">
                                                            <%=user.getEmail()%>
                                                        </td>
                                                        <td class="border px-6 py-2">
                                                            <%=user.getRole()%>
                                                        </td>
                                                        <td class="border px-6 py-2">
                                                            <%=user.getPassword()%>
                                                        </td>
                                                        <td class="border px-6 py-2">
                                                            <a href="/vouch/controller?action=updateUser&id=<%=user.getEmail()%>"
                                                                class="text-[12px] font-semibold text-blue-600">Edit</a>
                                                            &nbsp;
                                                            <a href="/vouch/controller?action=deleteUser&id=<%=user.getEmail()%>"
                                                                class="text-[12px] font-semibold text-red-600">Delete</a>
                                                        </td>
                                                    </tr>
                                                    <% }} %>
                                        </table>
                                    </div>
                                </section>
                    </div>
</body>

</html>