<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vouch</title>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>

</head>

<body>

    <%@page import="java.util.ArrayList, myPack.PostInfo" %>
        <% String role=(String)session.getAttribute("role"); if(role==null ) { %>
            <jsp:forward page="index.jsp" />
            <% } else if(role.equals("admin")) { %>
                <jsp:forward page="/adminDashboard/Dashboard.jsp" />
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
                                <li><a href="/vouch/userDashboard/CreatePost.jsp"
                                        class="p-5 pl-10 font-semibold block w-full bg-gray-300 hover:bg-gray-200 ">Create
                                        Post</a>
                                </li>
                                <li><a href="/vouch/controller?action=showUserPosts"
                                        class="p-5 pl-10 font-semibold block w-full bg-gray-300 hover:bg-gray-200 ">Your
                                        Posts</a>
                                </li>
                            </ul>
                            <a href="/vouch/controller?action=logout"
                                class="px-5 py-2 text-white inline-block mt-10 ml-10 rounded-lg bg-red-600 hover:bg-red-500">Logout</a>
                        </aside>


                        <section class="w-3/4 h-screen flex justify-center items-center">
                            <form method="post" class="gap-3 mt-3 w-fit flex flex-col"
                                action="/vouch/controller?action=createPost">
                                <h1 class="text-center font-semibold text-2xl">Create Your New Post</h1>
                                <input class="border-2 border-blue-500 focus:outline-none rounded-md p-2 pl-4"
                                    type="text" required placeholder="Title . . ." name="title" />
                                <textarea
                                    class="border-2 border-blue-500 focus:outline-none resize-none rounded-md p-2 h-[100px] overflow-hidden pl-4"
                                    type="text" required placeholder="Text . . ." name="content"></textarea>
                                <div class="flex gap-2">
                                    <input class="cursor-pointer bg-gray-600 text-white py-2 px-4 rounded-md w-1/2"
                                        type="submit" name="submitType" value="Draft">
                                    <input class="cursor-pointer bg-blue-600 text-white py-2 px-4 rounded-md w-1/2"
                                        type="submit" name="submitType" value="Submit">
                                </div>
                                <% String message=(String)request.getAttribute("message"); if(message!=null){ %>
                                    <p class="text-center text-red-600">
                                        <%=message%>
                                    </p>
                                    <%}%>
                            </form>
                        </section>



                    </div>
</body>

</html>