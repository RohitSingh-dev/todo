import { useScreenshot } from 'use-react-screenshot';

const ScreenshotButton = () => {
    const [image, takeScreenshot] = useScreenshot()
    const getImage = () => takeScreenshot(document.body)

  return (
      <div className="admin-dashboard">
          <header className="dashboard-header">
              <span>Admin Dashboard</span>
          </header>
          <main className="dashboard-content">
              <section className="crud-section">
                  <h2>Manage Users</h2>
                  <button onClick={() => alert('Add User')}>Add User</button>
                  <button onClick={() => alert('Edit User')}>Edit User</button>
                  <button onClick={() => alert('Delete User')}>Delete User</button>
              </section>

              <section className="crud-section">
                  <h2>Manage Products</h2>
                  <button onClick={() => alert('Add Product')}>Add Product</button>
                  <button onClick={() => alert('Edit Product')}>Edit Product</button>
                  <button onClick={getImage}>Take Screenshot</button>
                  <img src={image} alt={'Screenshot'} />
              </section>

              <section className="crud-section">
                  <h2>View Reports</h2>
                  <button onClick={() => alert('View Sales Report')}>Sales Report</button>
                  <button onClick={() => alert('View User Activity')}>User Activity</button>
              </section>

              <section className="crud-section">
                  <h2>Settings</h2>
                  <button onClick={() => alert('Update Settings')}>Update Settings</button>
              </section>
          </main>
      </div>
  )
}

export default ScreenshotButton